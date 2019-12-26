package ru.sibgu.doreon.coursework.code;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.sibgu.doreon.coursework.R;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Weather> weatherList;

    public WeatherAdapter(Context context, List<Weather> weatherList) {
        this.inflater = LayoutInflater.from(context);
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        Weather weather = weatherList.get(position);
        holder.cityView.setText(String.format("%s\n(%s)", weather.getCity(), new SimpleDateFormat("HH:mm:ss dd.MM", Locale.ROOT).format(weather.getDatestamp())));
        holder.weatherView.setText(weather.getWeather());
        holder.tempView.setText(weather.getTemp());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView cityView, weatherView, tempView;
        ViewHolder(View view){
            super(view);
            cityView = (TextView)view.findViewById(R.id.city_name);
            weatherView = (TextView) view.findViewById(R.id.city_weather);
            tempView = (TextView) view.findViewById(R.id.city_temp);
        }
    }
}

