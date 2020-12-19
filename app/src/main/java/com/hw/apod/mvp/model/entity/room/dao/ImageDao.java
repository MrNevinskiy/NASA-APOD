package com.hw.apod.mvp.model.entity.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.hw.apod.mvp.model.entity.room.RoomCacheImage;

import java.util.List;

@Dao
public interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RoomCacheImage image);

    @Update
    void update(RoomCacheImage image);

    @Delete
    void delete(RoomCacheImage image);

    @Query("DELETE FROM RoomCacheImage")
    void clear();

    @Query("SELECT * FROM RoomCacheImage WHERE url = :url LIMIT 1")
    RoomCacheImage findByUrl(String url);
}
