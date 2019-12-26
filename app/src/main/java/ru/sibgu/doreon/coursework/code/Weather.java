package ru.sibgu.doreon.coursework.code;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Weather {

    private String city, weather, temp;
    @PrimaryKey
    private long datestamp;

    public Weather(String city, String temp, String weather) {
        this.temp=temp;
        this.weather=weather;
        this.datestamp = System.currentTimeMillis();
        this.city = city;
    }


    public long getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(long datestamp) {
        this.datestamp = datestamp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
