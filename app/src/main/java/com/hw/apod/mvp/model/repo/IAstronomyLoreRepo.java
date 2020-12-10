package com.hw.apod.mvp.model.repo;

import com.hw.apod.mvp.model.entity.AstronomyLore;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface IAstronomyLoreRepo {
    Single<List<AstronomyLore>> getLore(String api_key, String date);
}
