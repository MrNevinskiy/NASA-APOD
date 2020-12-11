package com.hw.apod.mvp.presenter;

import android.util.Log;

import com.hw.apod.BuildConfig;
import com.hw.apod.mvp.model.entity.AstronomyLore;
import com.hw.apod.mvp.model.repo.IAstronomyLoreRepo;
import com.hw.apod.mvp.presenter.list.ISearchListPresenter;
import com.hw.apod.mvp.view.SearchView;
import com.hw.apod.mvp.view.list.SearchItemView;
import com.hw.apod.ui.navigation.Screens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class SearchPresenter extends MvpPresenter<SearchView> {
    private final String TAG = SearchPresenter.class.getSimpleName();

    private static final boolean VERBOSE = true;

    private Router router;
    private final Scheduler scheduler;
    private IAstronomyLoreRepo astronomyLoreRepo;

    public SearchPresenter(Scheduler scheduler, Router router, IAstronomyLoreRepo astronomyLoreRepo) {
        this.scheduler = scheduler;
        this.router = router;
        this.astronomyLoreRepo = astronomyLoreRepo;
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
            view.setDate(astronomyLore.getDate());
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
        loadData(" ");
    }

    public void loadData(String date) {
        Log.d(TAG, BuildConfig.NASASecAPIKEY + " - " + date);
        astronomyLoreRepo.getLore(BuildConfig.NASASecAPIKEY, date).observeOn(scheduler).subscribe(lore -> {
            dateListPresenter.lore.addAll(Collections.singleton(lore));
            getViewState().updateList();
        }, (e) -> {
            Log.w(TAG, "Error" + e.getMessage());
        });
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }

}
