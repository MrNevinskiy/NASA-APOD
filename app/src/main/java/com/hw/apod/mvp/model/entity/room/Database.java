package com.hw.apod.mvp.model.entity.room;

import androidx.room.RoomDatabase;

import com.hw.apod.mvp.model.entity.room.dao.LoreDao;

@androidx.room.Database(entities = {RoomAstronomyLore.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public static final String DB_NAME = "database.db";
    public abstract LoreDao loreDao();
}
