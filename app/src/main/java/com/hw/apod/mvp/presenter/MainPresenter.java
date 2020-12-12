package com.hw.apod.mvp.presenter;

import com.hw.apod.app.APODApplication;
import com.hw.apod.mvp.view.MainView;
import com.hw.apod.ui.navigation.Screens;

import javax.inject.Inject;

import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    Router router;

    public MainPresenter() {
        super();

        APODApplication.INSTANCE.getAppComponent().inject(this);
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router.replaceScreen(new Screens.APODSearchScreen());
    }

    public void backClicked() {
        router.exit();
    }
}
