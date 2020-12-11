package com.hw.apod.mvp.model.cache;

import com.hw.apod.mvp.model.entity.AstronomyLore;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface IAstronomyLoreCache {
    Single<AstronomyLore> getAstronomyLore();
    Completable putAstronomyLore(AstronomyLore astronomyLore);
}
