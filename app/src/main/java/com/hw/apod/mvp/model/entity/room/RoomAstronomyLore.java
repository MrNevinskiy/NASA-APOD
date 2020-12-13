package com.hw.apod.mvp.model.entity.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomAstronomyLore {

    @PrimaryKey
    @NonNull
    private String date;

    public String title;
    public String explanation;
    public String hdurl;
    public String url;


    public RoomAstronomyLore(@NonNull String date, String title, String explanation, String hdurl, String url) {
        this.date = date;
        this.title = title;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.url = url;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getUrl() {
        return url;
    }
    
}
