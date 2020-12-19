package com.hw.apod.mvp.presenter;

import com.hw.apod.BuildConfig;
import com.hw.apod.app.APODApplication;
import com.hw.apod.mvp.model.entity.AstronomyLore;
import com.hw.apod.mvp.model.repo.IAstronomyLoreRepo;
import com.hw.apod.mvp.presenter.list.ISearchListPresenter;
import com.hw.apod.mvp.view.SearchView;
import com.hw.apod.mvp.view.list.SearchItemView;
import com.hw.apod.mvp.view.log.ILogInfo;
import com.hw.apod.ui.navigation.Screens;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class SearchPresenter extends MvpPresenter<SearchView> {
    private final String TAG = SearchPresenter.class.getSimpleName();

    @Inject
    ILogInfo iLogInfo;

    @Inject
    Router router;

    @Inject
    Scheduler scheduler;

    @Inject
    IAstronomyLoreRepo astronomyLoreRepo;

    public SearchPresenter() {
        APODApplication.INSTANCE.initScreensSubcomponent().inject(this);
    }

    private class DateListPresenter implements ISearchListPresenter {
        private List<AstronomyLore> lore = new ArrayList<>();

        @Override
        public void onItemClick(SearchItemView view) {
            final AstronomyLore astronomyLore = lore.get(view.getPos());
            router.navigateTo(new Screens.APODDetailScreen(astronomyLore));
        }

        @Override
        public void bindView(SearchItemView view) {
            AstronomyLore astronomyLore = lore.get(view.getPos());
            view.setDate(astronomyLore.getTitle());
        }

        @Override
        public int getCount() {
            return lore.size();
        }
    }

    private DateListPresenter dateListPresenter = new DateListPresenter();

    public DateListPresenter getDateListPresenter() {
        return dateListPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        loadDataCache();
    }

    public void loadDataCache() {
        astronomyLoreRepo.getLoreCache().observeOn(scheduler).subscribe(lore ->{
            dateListPresenter.lore.clear();
            dateListPresenter.lore.addAll(lore);
            getViewState().updateList();
        }, (e) -> {
            iLogInfo.getLog(TAG,"Error :" + e.toString());
        });

    }

    public void loadData(String date) {
        astronomyLoreRepo.getLore(BuildConfig.NASASecAPIKEY, date).observeOn(scheduler).subscribe(lore -> {
            dateListPresenter.lore.add(lore);
            getViewState().updateList();
            router.navigateTo(new Screens.APODDetailScreen(lore));
        }, (e) -> {
            iLogInfo.getLog(TAG,"Error :" + e.toString());
        });
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }

}
