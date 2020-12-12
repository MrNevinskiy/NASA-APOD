package com.hw.apod.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface SearchView extends MvpView {
    void init();
    void updateList();
    void release();
}
