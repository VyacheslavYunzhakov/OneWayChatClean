package com.example.onewaychatclean.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.onewaychatclean.model.Item;


@Database(entities = {Item.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();
}
