package ru.sibgu.doreon.coursework.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import ru.sibgu.doreon.coursework.App;
import ru.sibgu.doreon.coursework.R;
import ru.sibgu.doreon.coursework.code.Functions;
import ru.sibgu.doreon.coursework.code.RoomDB;
import ru.sibgu.doreon.coursework.code.Weather;
import ru.sibgu.doreon.coursework.code.WeatherAdapter;
import ru.sibgu.doreon.coursework.code.WeatherDao;

public class HomeFragment extends Fragment {

    private List<Weather> weatherList = new ArrayList<>();
    private Functions func = new Functions();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        setInitialData();

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_now);
        WeatherAdapter adapter = new WeatherAdapter(this.getContext(), weatherList);
        recyclerView.setAdapter(adapter);

        return root;
    }

    private void setInitialData(){
        RoomDB db = App.getInstance().getDatabase();
        WeatherDao weatherDao = db.weatherDao();

        List<Integer> values = new ArrayList<>();
        weatherList = new ArrayList<>();
        List<String> cities = new ArrayList<>();
        cities.add("Красноярск"); //1502026
        values.add(1502026);
        cities.add("Томск"); //1489425
        values.add(1489425);
        cities.add("Новосибирск"); //1496747
        values.add(1496747);
        cities.add("Барнаул"); //1510853
        values.add(1510853);
        StringBuilder payload = new StringBuilder("group?id=");
        for (int id : values) {
            payload.append(id).append(values.indexOf(id) == values.size()-1 ? "&units=metric&appid="+getResources().getString(R.string.api_key) : ",");
        }
        JSONObject json = func.request_task(getResources().getString(R.string.weather_url),payload.toString());
        Log.i("JSON", json.toString());

        JSONArray array = null;
        try {
            array = json.getJSONArray("list");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                Weather weather = new Weather(cities.get(i), "NaN","JSON parse error");
                try {
                    weather.setTemp(array.getJSONObject(i).getJSONObject("main").getString("temp"));
                    weather.setWeather(array.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                weatherList.add(weather);
                weatherDao.insert(weather);
            }
        }

    }

}