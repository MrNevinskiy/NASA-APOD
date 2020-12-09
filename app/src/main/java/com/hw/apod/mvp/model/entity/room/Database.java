package com.hw.apod.mvp.model.entity.room;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hw.apod.app.APODApplication;
import com.hw.apod.mvp.model.entity.room.dao.LoreDao;

@androidx.room.Database(entities = {RoomAstronomyLore.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public static final String DB_NAME = "database.db";
    private static volatile Database INSTANCE;

    public abstract LoreDao loreDao();

    public static Database getInstance() {
        Database refLocal = INSTANCE;
        if (refLocal == null) {
            synchronized (Database.class) {
                INSTANCE = refLocal;

                if (refLocal == null) {
                    INSTANCE = refLocal = Room.databaseBuilder(APODApplication.INSTANCE, Database.class, DB_NAME).build();
                }
            }
        }

        return refLocal;
    }
}
