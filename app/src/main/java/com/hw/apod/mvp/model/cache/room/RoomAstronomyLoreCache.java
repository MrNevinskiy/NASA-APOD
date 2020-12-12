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
                Log.d("RoomAstronomyLoreCache ",roomAstronomyLore.getDate());
            }
            return lore;
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

            Log.d("RoomAstronomyLoreCache",astronomyLore.getDate());

            roomLore.add(roomAstronomyLore);

            db.loreDao().insert(roomLore);

        }).subscribeOn(Schedulers.io());
    }
}

