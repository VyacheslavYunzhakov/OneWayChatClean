package com.example.onewaychatclean.database;

import android.app.Application;

import androidx.room.Room;

import com.example.onewaychatclean.chat.AppComponent;
import com.example.onewaychatclean.chat.DaggerAppComponent;


public class App extends Application {
    public static App instance;

    private static AppComponent component;
    private AppDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
        component = DaggerAppComponent.create();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public static AppComponent getComponent() {
        return component;
    }
}
