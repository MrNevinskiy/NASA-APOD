package com.hw.apod.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AstronomyLore implements Parcelable {
    @SerializedName("date")
    @Expose private String date;

    @SerializedName("explanation")
    @Expose private String explanation;

    @SerializedName("hdurl")
    @Expose private String hdurl;

    @SerializedName("title")
    @Expose private String title;

    @SerializedName("url")
    @Expose private String url;

    public AstronomyLore(String date, String explanation, String hdurl, String title, String url) {
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.title = title;
        this.url = url;
    }

    protected AstronomyLore(Parcel in) {
        date = in.readString();
        explanation = in.readString();
        hdurl = in.readString();
        title = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(explanation);
        dest.writeString(hdurl);
        dest.writeString(title);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AstronomyLore> CREATOR = new Creator<AstronomyLore>() {
        @Override
        public AstronomyLore createFromParcel(Parcel in) {
            return new AstronomyLore(in);
        }

        @Override
        public AstronomyLore[] newArray(int size) {
            return new AstronomyLore[size];
        }
    };

    public String getDate() {
        return date;
    }
    

    public String getExplanation() {
        return explanation;
    }
    

    public String getHdurl() {
        return hdurl;
    }
    

    public String getTitle() {
        return title;
    }
    

    public String getUrl() {
        return url;
    }

}
