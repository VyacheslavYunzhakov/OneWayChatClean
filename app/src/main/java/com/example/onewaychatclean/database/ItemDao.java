package com.example.onewaychatclean.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.onewaychatclean.model.Item;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM item ORDER BY time")
    Flowable<List<Item>> getAll();

    @Query("SELECT * FROM item WHERE time = (SELECT MAX(time) FROM item) ")
    Flowable<Item> getLast();

    @Insert
    void insertOne (Item message);
    @Insert
    void insert (List<Item> messages);
}
