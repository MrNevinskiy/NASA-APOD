package com.hw.apod.mvp.model.entity.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomCacheImage {

    @NonNull
    @PrimaryKey
    public String url;

    public String localPath;

    public RoomCacheImage(String url, String localPath) {
        this.url = url;
        this.localPath = localPath;
    }

    public String getUrl() {
        return url;
    }

    public String getLocalPath() {
        return localPath;
    }
}
