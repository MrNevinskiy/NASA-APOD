package com.hw.apod.app;

import android.app.Application;

import com.hw.apod.mvp.model.api.IDataSource;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class APODApplication extends Application {

    public static  APODApplication INSTANCE;

    private ApiHolder apiHolder;

    private Cicerone<Router> cicerone;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        initCicerone();
        apiHolder = new ApiHolder();
    }

    public static APODApplication getInstance(){
        return INSTANCE;
    }

    private void initCicerone() {
        cicerone = Cicerone.create();
    }

    public Router getRouter() {
        return cicerone.getRouter();
    }

    public NavigatorHolder getNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

    public IDataSource getApi() {
        return apiHolder.getDataSource();
    }
}
