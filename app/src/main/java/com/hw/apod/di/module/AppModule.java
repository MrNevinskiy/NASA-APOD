package com.hw.apod.di.module;

import com.hw.apod.app.APODApplication;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;

@Module
public class AppModule {
    private APODApplication app;

    public AppModule(APODApplication app) {
        this.app = app;
    }

    @Provides
    public APODApplication app(){
        return app;
    }

    @Provides
    public Scheduler mainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
