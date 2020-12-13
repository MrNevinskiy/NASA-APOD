package com.hw.apod.mvp.model.cache.room;

import android.util.Log;

import com.hw.apod.mvp.model.cache.IAstronomyLoreCache;
import com.hw.apod.mvp.model.entity.AstronomyLore;
import com.hw.apod.mvp.model.entity.room.Database;
import com.hw.apod.mvp.model.entity.room.RoomAstronomyLore;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RoomAstronomyLoreCache implements IAstronomyLoreCache {
    private String TAG = RoomAstronomyLoreCache.class.getSimpleName();

    private final Database db;

    public RoomAstronomyLoreCache(Database db) {
        this.db = db;
    }

    @Override
    public Single<List<AstronomyLore>> getAstronomyLore() {
        return Single.fromCallable(() -> {
            List<RoomAstronomyLore> roomLore = db.loreDao().getAll();

            List<AstronomyLore> lore = new ArrayList<>();

            for (RoomAstronomyLore roomAstronomyLore: roomLore) {
                AstronomyLore astronomyLore = new AstronomyLore(
                        roomAstronomyLore.getDate(),
                        roomAstronomyLore.getExplanation(),
                        roomAstronomyLore.getHdurl(),
                        roomAstronomyLore.getTitle(),
                        roomAstronomyLore.getUrl());

                lore.add(astronomyLore);

                Log.d(TAG,roomAstronomyLore.getTitle());

            }
            return lore;
        });
    }

    @Override
    public Completable putAstronomyLore(AstronomyLore astronomyLore) {
        return Completable.fromAction(() -> {

            RoomAstronomyLore roomAstronomyLore = new RoomAstronomyLore(
                    astronomyLore.getDate(),
                    astronomyLore.getTitle(),
                    astronomyLore.getExplanation(),
                    astronomyLore.getHdurl(),
                    astronomyLore.getUrl());

            Log.d(TAG,astronomyLore.getDate());

            db.loreDao().insert(roomAstronomyLore);

            Log.d(TAG,db.loreDao().getSingle().title);

        }).subscribeOn(Schedulers.io());
    }
}

