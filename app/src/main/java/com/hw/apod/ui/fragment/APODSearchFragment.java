package com.hw.apod.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hw.apod.R;
import com.hw.apod.app.APODApplication;
import com.hw.apod.mvp.model.cache.room.RoomAstronomyLoreCache;
import com.hw.apod.mvp.model.entity.room.Database;
import com.hw.apod.mvp.model.repo.IAstronomyLoreRepo;
import com.hw.apod.mvp.model.repo.RetrofitAstronomyLoreRepo;
import com.hw.apod.mvp.presenter.SearchPresenter;
import com.hw.apod.mvp.view.SearchView;
import com.hw.apod.ui.BackButtonListener;
import com.hw.apod.ui.adapter.SearchRVAdapter;
import com.hw.apod.ui.network.AndroidNetworkStatus;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.terrakok.cicerone.Router;

public class APODSearchFragment extends MvpAppCompatFragment implements SearchView, BackButtonListener {

    private Button searchButton;
    private CalendarView calendarView;
    private RecyclerView recyclerView;
    private SearchRVAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private View view;

    private String date;

    @InjectPresenter
    SearchPresenter detailPresenter;

    @ProvidePresenter
    public static APODSearchFragment getInstance(){
        APODSearchFragment fragment = new APODSearchFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_apod_search, container, false);
        searchButton = (Button)view.findViewById(R.id.search);
        calendarView = (CalendarView)view.findViewById(R.id.calendarView);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public boolean backPressed() {
        return detailPresenter.backPressed();
    }

    @Override
    public void init() {
        initCalendarView();
        initSearchButton();
        initRV();
    }


    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void release() {
        APODApplication.INSTANCE.releaseSearchSubcomponent();
    }

    public void initRV(){
        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new SearchRVAdapter(detailPresenter.getDateListPresenter());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void initSearchButton(){
        searchButton.setOnClickListener(view -> detailPresenter.loadData(date));
    }

    public void initCalendarView() {
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            date = new StringBuilder()
                    .append(year).append("-")
                    .append(month + 1).append("-")
                    .append(dayOfMonth).toString();
            Toast.makeText(getContext().getApplicationContext(), date, Toast.LENGTH_LONG).show();
        });
    }
}
