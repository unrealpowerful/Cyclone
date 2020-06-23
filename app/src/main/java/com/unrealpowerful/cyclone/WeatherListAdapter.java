package com.unrealpowerful.cyclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater lInflater;
    ArrayList<WeatherInfo> objects;
    public WeatherListAdapter(Context context, ArrayList<WeatherInfo> wInfo) {
        this.context = context;
        this.objects = wInfo;
        this.lInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if(view == null)
            view = lInflater.inflate(R.layout.weather_item, parent, false);
        WeatherInfo weatherInfo = getWeatherInfo(i);
        ((ImageView)view.findViewById(R.id.listWeatherImage)).setImageResource(weatherInfo.getIcon(weatherInfo.icon));
        ((TextView)view.findViewById(R.id.dayText)).setText(((weatherInfo.time).substring(0, 16).replace("-", "/")));
        ((TextView)view.findViewById(R.id.listTemp)).setText((int)weatherInfo.getTempCelsius() + "\u2103");
        ((TextView)view.findViewById(R.id.listDescription)).setText(weatherInfo.getDescription());
        return view;
    }
    WeatherInfo getWeatherInfo(int position) {
        return ((WeatherInfo) getItem(position));
    }
}
