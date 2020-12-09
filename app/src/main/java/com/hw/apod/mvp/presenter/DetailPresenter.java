package com.hw.apod.mvp.presenter;

import com.hw.apod.mvp.model.entity.AstronomyLore;
import com.hw.apod.mvp.view.DetailView;

import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class DetailPresenter extends MvpPresenter<DetailView> {

    private Router router;
    private AstronomyLore astronomyLore;

    public DetailPresenter(Router router, AstronomyLore astronomyLore) {
        this.router = router;
        this.astronomyLore = astronomyLore;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        String date = astronomyLore.getDate();
        String title = astronomyLore.getTitle();
        String explanation = astronomyLore.getExplanation();
        String hdurl = astronomyLore.getHdurl();
        String url = astronomyLore.getUrl();

        getViewState().setDate(date != null? date : "");
        getViewState().setExplanation(explanation != null? explanation : "");
        getViewState().setTitle(title != null? title : "");
        getViewState().setHDurl(hdurl);
        getViewState().setUrl(url);
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
