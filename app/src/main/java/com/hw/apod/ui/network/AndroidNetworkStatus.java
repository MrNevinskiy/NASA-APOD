package com.hw.apod.ui.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;

import com.hw.apod.app.APODApplication;
import com.hw.apod.mvp.model.network.INetworkStatus;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class AndroidNetworkStatus implements INetworkStatus {

    private BehaviorSubject<Boolean> statusObject = BehaviorSubject.create();

    public AndroidNetworkStatus() {
        statusObject.onNext(false);

        ConnectivityManager connectivityManager =(ConnectivityManager) APODApplication.INSTANCE
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkRequest networkRequest = new NetworkRequest.Builder().build();

        connectivityManager.registerNetworkCallback(networkRequest, new ConnectivityManager.NetworkCallback(){
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                statusObject.onNext(true);
            }

            @Override
            public void onUnavailable() {
                super.onUnavailable();
                statusObject.onNext(false);
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                statusObject.onNext(false);
            }
        });
    }

    @Override
    public Single<Boolean> isOnlineSingle() {
        return statusObject.first(false);
    }
}
