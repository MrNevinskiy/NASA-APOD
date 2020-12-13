package com.hw.apod.mvp.model.entity.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.hw.apod.mvp.model.entity.room.RoomAstronomyLore;

import java.util.List;

@Dao
public interface LoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RoomAstronomyLore roomAstronomyLore);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<RoomAstronomyLore> roomAstronomyLore);

    @Delete
    void delete(RoomAstronomyLore roomAstronomyLore);
    @Delete
    void delete(List<RoomAstronomyLore> roomAstronomyLore);

    @Query("SELECT * FROM RoomAstronomyLore")
    List<RoomAstronomyLore> getAll();
    @Query("SELECT * FROM RoomAstronomyLore")
    RoomAstronomyLore getSingle();

}
