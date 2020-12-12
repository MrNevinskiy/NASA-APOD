package com.hw.apod.di.module;

import android.widget.ImageView;

import com.hw.apod.mvp.view.image.GlideImageLoader;
import com.hw.apod.mvp.view.image.IImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageModule {
    @Singleton
    @Provides
    IImageLoader<ImageView> getImageLoader() {
        return new GlideImageLoader();
    }
}
