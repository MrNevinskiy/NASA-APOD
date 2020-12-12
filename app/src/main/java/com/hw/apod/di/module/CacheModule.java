package com.hw.apod.di.module;

import androidx.room.Room;

import com.hw.apod.app.APODApplication;
import com.hw.apod.mvp.model.entity.room.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {
    @Singleton
    @Provides
    Database database() {
        return Room.databaseBuilder(APODApplication.INSTANCE, Database.class, Database.DB_NAME)
                .build();
    }
}
