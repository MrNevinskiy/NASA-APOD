package com.hw.apod.mvp.presenter.list;

import com.hw.apod.mvp.view.list.IItemView;

public interface IListPresenter<V extends IItemView> {
    void onItemClick(V view);
    void bindView(V view);
    int getCount();
}
