package com.hw.apod.mvp.model.repo;

import com.hw.apod.mvp.model.api.IDataSource;
import com.hw.apod.mvp.model.cache.IAstronomyLoreCache;
import com.hw.apod.mvp.model.entity.AstronomyLore;
import com.hw.apod.mvp.model.network.INetworkStatus;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RetrofitAstronomyLoreRepo implements IAstronomyLoreRepo {

    private IDataSource api;
    private INetworkStatus status;
    final IAstronomyLoreCache cache;
    private Disposable disposable = null;

    public RetrofitAstronomyLoreRepo(IDataSource api, INetworkStatus status, IAstronomyLoreCache cache) {
        this.api = api;
        this.status = status;
        this.cache = cache;
    }

    @Override
    public Single<List<AstronomyLore>> getLore(String date) {
        return status.isOnlineSingle().flatMap((isOnline) -> {
            if (isOnline) {
                return api.loadLore(date).flatMap((lore) -> {
                    return cache.putAstronomyLore(lore).toSingleDefault(lore);
                });
            } else {
                return cache.getAstronomyLore();
            }
        }).subscribeOn(Schedulers.io());
    }
}
