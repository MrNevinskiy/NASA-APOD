package com.hw.apod.di.screens;

import com.hw.apod.di.screens.module.ScreensModule;
import com.hw.apod.mvp.presenter.DetailPresenter;
import com.hw.apod.mvp.presenter.SearchPresenter;
import com.hw.apod.ui.fragment.APODDetailFragment;

import dagger.Subcomponent;

@ScreensScoup
@Subcomponent(
        modules = {
                ScreensModule.class,
        }
)
public interface ScreensSubcomponent {
    void inject(SearchPresenter searchPresenter);
    void inject(DetailPresenter detailPresenter);

    /**
     * Заинектил его для того чтобы создать Glide
     * @param apodDetailFragment
     */
    void inject(APODDetailFragment apodDetailFragment);
}
