package com.hw.apod.mvp.model.cache.room;

import com.hw.apod.mvp.model.cache.IAstronomyLoreCache;
import com.hw.apod.mvp.model.entity.AstronomyLore;
import com.hw.apod.mvp.model.entity.room.Database;
import com.hw.apod.mvp.model.entity.room.RoomAstronomyLore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RoomAstronomyLoreCache implements IAstronomyLoreCache {

    private final Database db;

    public RoomAstronomyLoreCache(Database db) {
        this.db = db;
    }


    @Override
    public Single<AstronomyLore> getAstronomyLore() {
        return Single.fromCallable(() -> {
            RoomAstronomyLore roomLore = db.loreDao().getAll();

            AstronomyLore astronomyLore = new AstronomyLore(
                    roomLore.getDate(),
                    roomLore.getExplanation(),
                    roomLore.getHdurl(),
                    roomLore.getTitle(),
                    roomLore.getUrl());

            return astronomyLore;
        });
    }


    @Override
    public Completable putAstronomyLore(AstronomyLore astronomyLore) {
        return Completable.fromAction(() -> {

            List<RoomAstronomyLore> roomLore = new ArrayList<>();

            RoomAstronomyLore roomAstronomyLore = new RoomAstronomyLore(
                    astronomyLore.getDate(),
                    astronomyLore.getTitle(),
                    astronomyLore.getExplanation(),
                    astronomyLore.getHdurl(),
                    astronomyLore.getUrl());

            roomLore.add(roomAstronomyLore);

            db.loreDao().insert(roomLore);

        }).subscribeOn(Schedulers.io());
    }
}

