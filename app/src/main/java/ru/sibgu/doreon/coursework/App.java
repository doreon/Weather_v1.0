package ru.sibgu.doreon.coursework;

import android.app.Application;

import androidx.room.*;
import ru.sibgu.doreon.coursework.code.RoomDB;

public class App extends Application {

    public static App instance;

    private RoomDB database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, RoomDB.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public RoomDB getDatabase() {
        return database;
    }
}