package com.hw.apod.mvp.model.network;

import io.reactivex.rxjava3.core.Single;

public interface INetworkStatus {
    Single<Boolean> isOnlineSingle();
}
