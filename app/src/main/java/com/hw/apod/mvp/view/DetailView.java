package com.hw.apod.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface DetailView extends MvpView {
    void setDate(String date);
    void setTitle(String title);
    void setExplanation(String explanation);
    void setUrl(String url);
    void setHDurl(String hdurl);
}
