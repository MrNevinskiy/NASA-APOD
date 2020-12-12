package com.hw.apod.di;

import com.hw.apod.di.module.ApiModule;
import com.hw.apod.di.module.AppModule;
import com.hw.apod.di.module.CacheModule;
import com.hw.apod.di.module.CiceroneModule;
import com.hw.apod.di.screens.ScreensSubcomponent;
import com.hw.apod.mvp.presenter.MainPresenter;
import com.hw.apod.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApiModule.class,
                AppModule.class,
                CacheModule.class,
                CiceroneModule.class,
        }
)

public interface AppComponent {
        ScreensSubcomponent searchSubcomponent();

        void inject(MainActivity mainActivity);
        void inject(MainPresenter mainPresenter);

}
