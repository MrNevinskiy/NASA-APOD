package com.hw.apod.mvp.model.api;

import com.hw.apod.mvp.model.entity.AstronomyLore;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IDataSource {

    @GET("/apod")
    Single<List<AstronomyLore>> loadLore(@Query("date") String date, @Query("api") String keyApi);
}
