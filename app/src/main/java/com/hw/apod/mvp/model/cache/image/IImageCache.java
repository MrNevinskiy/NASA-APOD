package com.hw.apod.mvp.model.cache.image;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface IImageCache {
    Single<Boolean> contains(String url);
    Maybe getBytes(String url);
    Completable saveImage(String url, byte[] byteArray);
    Completable clear();
}
