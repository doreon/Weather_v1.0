package ru.sibgu.doreon.coursework.ui.history;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.Trace;

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

public class HistoryFragment extends Fragment {

    private List<Weather> weatherList = new ArrayList<>();
    private Functions func = new Functions();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);

        setInitialData();

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_history);
        WeatherAdapter adapter = new WeatherAdapter(this.getContext(), weatherList);
        recyclerView.setAdapter(adapter);

        return root;
    }

    private void setInitialData(){
        Trace myTrace = FirebasePerformance.getInstance().newTrace("test_trace");
        myTrace.start();
        RoomDB db = App.getInstance().getDatabase();
        WeatherDao weatherDao = db.weatherDao();
        weatherList = weatherDao.getAll();
        myTrace.incrementMetric("Weather's Looked At All Time", weatherList.size());
        myTrace.stop();
//
//        for (Weather w : weatherList) {
//            weatherDao.delete(w);
//        }
//        weatherList = weatherDao.getAll();
    }

}