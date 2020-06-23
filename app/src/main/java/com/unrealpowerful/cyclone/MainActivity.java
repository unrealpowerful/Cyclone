package com.unrealpowerful.cyclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView currentTemp;
    private TextView currentDescription;
    private Button buttonCityList;
    private ImageButton buttonMap;
    private ImageView currentWeatherImage;
    private WeatherInfo weatherInfo;
    private WeatherInfo[] timeWeatherInfo = new WeatherInfo[40];
    private ListView weatherList;
    private WeatherListAdapter listWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Style/Animation
        ConstraintLayout constraintLayout = findViewById(R.id.MainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        Intent extras = getIntent();
        currentTemp = findViewById(R.id.currentTemp);
        currentWeatherImage = findViewById(R.id.cWeatherImage);
        currentDescription = findViewById(R.id.cDescription);
        buttonCityList = findViewById(R.id.bCityList);
        buttonMap = findViewById(R.id.bMap);
        weatherList = findViewById(R.id.weatherList);

        if (extras.hasExtra("cityID")) {
            weatherInfo = new WeatherInfo();
            weatherInfo.city = weatherInfo.getCityNameById(extras.getIntExtra("cityID", 0));
            updateInfo(String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=3007156c4d9747d2260a99b96d4841f3", weatherInfo.city));
            updateInfo(String.format("http://api.openweathermap.org/data/2.5/forecast?q=%s&appid=3007156c4d9747d2260a99b96d4841f3", weatherInfo.city));
        }

        buttonCityList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cityList = new Intent(MainActivity.this, CityList.class);
                startActivity(cityList);
            }
        });

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    protected void updateInfo(String url)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response != null) {
                        if(response.has("list"))
                        {
                            ArrayList<WeatherInfo> wInfo = new ArrayList<WeatherInfo>();
                            for(int i = 0; i < response.getJSONArray("list").length(); i++)
                            {
                                timeWeatherInfo[i] = new WeatherInfo();
                                timeWeatherInfo[i].city = response.getJSONObject("city").getString("name");
                                timeWeatherInfo[i].temp = response.getJSONArray("list").getJSONObject(i).getJSONObject("main").getDouble("temp");
                                timeWeatherInfo[i].icon = response.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon");
                                timeWeatherInfo[i].time = response.getJSONArray("list").getJSONObject(i).getString("dt_txt");
                                wInfo.add(timeWeatherInfo[i]);
                            }
                            listWeatherAdapter = new WeatherListAdapter(MainActivity.this, wInfo);
                            weatherList.setAdapter(listWeatherAdapter);
                        } else {
                            weatherInfo.city = response.getString("name");
                            weatherInfo.temp = response.getJSONObject("main").getDouble("temp");
                            weatherInfo.icon = response.getJSONArray("weather").getJSONObject(0).getString("icon");
                        }
                        currentTemp.setText((int)weatherInfo.getTempCelsius() + "\u2103");
                        currentWeatherImage.setImageResource(weatherInfo.getIcon(weatherInfo.icon));
                        currentDescription.setText(weatherInfo.getDescription());
                        buttonCityList.setText(weatherInfo.translateCityToRussian(weatherInfo.city));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
}