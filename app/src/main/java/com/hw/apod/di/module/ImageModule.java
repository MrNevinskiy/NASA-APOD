package com.hw.apod.di.module;

import android.widget.ImageView;

import com.hw.apod.app.APODApplication;
import com.hw.apod.mvp.model.cache.image.IImageCache;
import com.hw.apod.mvp.model.cache.image.room.RoomImageCache;
import com.hw.apod.mvp.model.entity.room.Database;
import com.hw.apod.mvp.model.network.INetworkStatus;
import com.hw.apod.mvp.view.image.GlideImageLoader;
import com.hw.apod.mvp.view.image.IImageLoader;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageModule {

    @Named("cacheDir")
    @Singleton
    @Provides
    File cacheDir(APODApplication app){
        return app.getCacheDir();
    }


    @Singleton
    @Provides
    IImageCache imageCache(Database db, @Named("cacheDir") File cacheDir){
        return new RoomImageCache(db, cacheDir);
    }

    @Singleton
    @Provides
    IImageLoader<ImageView> getImageLoader(INetworkStatus networkStatus, IImageCache imageCache) {
        return new GlideImageLoader(imageCache,networkStatus);
    }
}
