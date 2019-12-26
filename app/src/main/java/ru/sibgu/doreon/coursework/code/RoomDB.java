package ru.sibgu.doreon.coursework.code;

import androidx.room.*;

@Database(entities = {Weather.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}