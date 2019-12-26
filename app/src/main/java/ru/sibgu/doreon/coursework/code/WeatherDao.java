package ru.sibgu.doreon.coursework.code;

import java.util.List;

import androidx.room.*;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM weather")
    List<Weather> getAll();

//    @Query("SELECT * FROM weather WHERE datestamp = :id")
//    Weather getByKey(long id);

    @Insert
    void insert(Weather weather);

    @Delete
    void delete(Weather weather);

}
