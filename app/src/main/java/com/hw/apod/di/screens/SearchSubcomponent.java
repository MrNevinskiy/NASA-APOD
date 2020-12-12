package com.hw.apod.di.screens;

import com.hw.apod.di.screens.module.ImageModule;
import com.hw.apod.di.screens.module.SearchModule;
import com.hw.apod.mvp.presenter.DetailPresenter;
import com.hw.apod.mvp.presenter.SearchPresenter;
import com.hw.apod.ui.fragment.APODDetailFragment;

import dagger.Subcomponent;

@SearchScoup
@Subcomponent(
        modules = {
                SearchModule.class,
                ImageModule.class
        }
)
public interface SearchSubcomponent {
    void inject(SearchPresenter searchPresenter);
    void inject(DetailPresenter detailPresenter);
    void inject(APODDetailFragment apodDetailFragment);
}
