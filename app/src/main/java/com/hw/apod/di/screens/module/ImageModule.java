package com.hw.apod.di.screens.module;

import android.widget.ImageView;

import com.hw.apod.mvp.view.image.GlideImageLoader;
import com.hw.apod.mvp.view.image.IImageLoader;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageModule {

    @Provides
    IImageLoader<ImageView> getImageLoader() {
        return new GlideImageLoader();
    }
}
