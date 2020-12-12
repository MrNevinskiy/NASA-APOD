package com.hw.apod.mvp.presenter;

import com.hw.apod.app.APODApplication;
import com.hw.apod.mvp.model.entity.AstronomyLore;
import com.hw.apod.mvp.view.DetailView;

import javax.inject.Inject;

import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class DetailPresenter extends MvpPresenter<DetailView> {

    @Inject
    Router router;

    private AstronomyLore astronomyLore;

    public DetailPresenter(AstronomyLore astronomyLore) {
        this.astronomyLore = astronomyLore;
        APODApplication.INSTANCE.initScreensSubcomponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        String date = astronomyLore.getDate();
        String title = astronomyLore.getTitle();
        String explanation = astronomyLore.getExplanation();
        String url = astronomyLore.getUrl();

        getViewState().setDate(date != null? date : "");
        getViewState().setExplanation(explanation != null? explanation : "");
        getViewState().setTitle(title != null? title : "");
        getViewState().setUrl(url);
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
