package com.hw.apod.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hw.apod.R;
import com.hw.apod.app.APODApplication;
import com.hw.apod.mvp.model.entity.AstronomyLore;
import com.hw.apod.mvp.presenter.DetailPresenter;
import com.hw.apod.mvp.view.DetailView;
import com.hw.apod.mvp.view.image.IImageLoader;
import com.hw.apod.ui.BackButtonListener;

import javax.inject.Inject;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class APODDetailFragment extends MvpAppCompatFragment implements DetailView, BackButtonListener {

    private static final String ARG = "astronomyLore";

    private View view;

    @Inject
    IImageLoader<ImageView> imageLoader;

    private TextView tv_date;
    private TextView tv_title;
    private TextView tv_explanation;
    private ImageView iv_hdurl;
    private ImageView iv_url;

    @InjectPresenter
    DetailPresenter detailPresenter;

    @ProvidePresenter
    DetailPresenter apodDetailFragment() {
        final AstronomyLore astronomyLore = getArguments().getParcelable(ARG);
        return new DetailPresenter(astronomyLore);
    }

    APODDetailFragment(){
        APODApplication.INSTANCE.initScreensSubcomponent().inject(this);
    }

    public static Fragment newInstance(AstronomyLore astronomyLore) {
        APODDetailFragment fragment = new APODDetailFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG, astronomyLore);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public boolean backPressed() {
        return detailPresenter.backPressed();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_apod_detail, container, false);

        tv_date = (TextView) view.findViewById(R.id.tv_date);
        tv_explanation = (TextView) view.findViewById(R.id.tv_explanation);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        iv_hdurl = (ImageView) view.findViewById(R.id.iv_hdurl);
        iv_url = (ImageView) view.findViewById(R.id.iv_url);

        return view;
    }


    @Override
    public void release() {
        APODApplication.INSTANCE.releaseScreensSubcomponent();
    }

    @Override
    public void setDate(String date) {
        tv_date.setText(date);
    }

    @Override
    public void setTitle(String title) {
        tv_title.setText(title);
    }

    @Override
    public void setExplanation(String explanation) {
        tv_explanation.setText(explanation);
    }

    @Override
    public void setUrl(String url) {
        imageLoader.loadImage(url, iv_url);
    }

    @Override
    public void setHDurl(String hdurl) {
        imageLoader.loadImage(hdurl, iv_hdurl);
    }

}
