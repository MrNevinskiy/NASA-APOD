package com.hw.apod.di.screens.module;


import com.hw.apod.di.screens.ScreensScoup;
import com.hw.apod.mvp.model.api.IDataSource;
import com.hw.apod.mvp.model.cache.IAstronomyLoreCache;
import com.hw.apod.mvp.model.cache.room.RoomAstronomyLoreCache;
import com.hw.apod.mvp.model.entity.room.Database;
import com.hw.apod.mvp.model.network.INetworkStatus;
import com.hw.apod.mvp.model.repo.IAstronomyLoreRepo;
import com.hw.apod.mvp.model.repo.RetrofitAstronomyLoreRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class ScreensModule {

    @Provides
    IAstronomyLoreCache astronomyLoreCache(Database db){
        return new RoomAstronomyLoreCache(db);
    }

    @ScreensScoup
    @Provides
    public IAstronomyLoreRepo astronomyLoreRepo(IDataSource api, INetworkStatus networkStatus, IAstronomyLoreCache astronomyLoreCache){
        return new RetrofitAstronomyLoreRepo(api,networkStatus,astronomyLoreCache);
    }

}
