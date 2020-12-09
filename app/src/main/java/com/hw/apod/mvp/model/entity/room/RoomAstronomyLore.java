package com.hw.apod.mvp.model.entity.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class RoomAstronomyLore {

    @PrimaryKey
    @NonNull
    private String date;

    private String title;
    private String explanation;
    private String hdurl;
    private String url;

    public RoomAstronomyLore(){}

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

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
