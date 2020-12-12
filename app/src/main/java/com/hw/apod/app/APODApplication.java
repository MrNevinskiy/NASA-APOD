package com.hw.apod.app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hw.apod.di.AppComponent;
import com.hw.apod.di.DaggerAppComponent;
import com.hw.apod.di.module.AppModule;
import com.hw.apod.di.screens.ScreensSubcomponent;

public class APODApplication extends Application {

    public static  APODApplication INSTANCE;

    private AppComponent appComponent;

    @Nullable
    private ScreensSubcomponent screensSubcomponent;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static APODApplication getInstance(){
        return INSTANCE;
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }

    @NonNull
    public ScreensSubcomponent initScreensSubcomponent(){
        AppComponent appComp = this.appComponent;

        if (appComp == null) {
            throw new IllegalStateException("appComponent must be initialized");
        }

        if (screensSubcomponent == null) {
            ScreensSubcomponent screensSubcomponent = appComp.searchSubcomponent();
            this.screensSubcomponent = screensSubcomponent;
        }

        return screensSubcomponent;
    }

    public void releaseScreensSubcomponent(){
        screensSubcomponent = null;
    }
}
