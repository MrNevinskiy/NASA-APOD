package com.hw.apod.di.module;

import com.hw.apod.mvp.view.log.ILogInfo;
import com.hw.apod.ui.util.LogInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LogModule {
    @Singleton
    @Provides
    ILogInfo getLogInfo(){
        return new LogInfo();
    }
}
