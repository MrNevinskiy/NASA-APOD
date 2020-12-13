package com.hw.apod.mvp.presenter;

import android.util.Log;

import com.hw.apod.BuildConfig;
import com.hw.apod.app.APODApplication;
import com.hw.apod.mvp.model.entity.AstronomyLore;
import com.hw.apod.mvp.model.repo.IAstronomyLoreRepo;
import com.hw.apod.mvp.presenter.list.ISearchListPresenter;
import com.hw.apod.mvp.view.SearchView;
import com.hw.apod.mvp.view.list.SearchItemView;
import com.hw.apod.ui.navigation.Screens;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class SearchPresenter extends MvpPresenter<SearchView> {
    private final String TAG = SearchPresenter.class.getSimpleName();

    private static final boolean VERBOSE = true;

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
            if (VERBOSE) {
                Log.v(TAG, " onItemClick " + view.getPos());
            }
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
            Log.w(TAG, "Error" + e.getMessage());
        });

    }

    public void loadData(String date) {
        Log.d(TAG, BuildConfig.NASASecAPIKEY + " - " + date);
        astronomyLoreRepo.getLore(BuildConfig.NASASecAPIKEY, date).observeOn(scheduler).subscribe(lore -> {
            dateListPresenter.lore.add(lore);
            getViewState().updateList();
            router.navigateTo(new Screens.APODDetailScreen(lore));
        }, (e) -> {
            Log.w(TAG, "Error" + e.getMessage());
        });
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }

}
