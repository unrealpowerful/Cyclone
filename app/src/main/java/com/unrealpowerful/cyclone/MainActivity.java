package com.unrealpowerful.cyclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView currentTemp;
    private TextView cityName;
    private TextView currentDescription;
    private ImageButton buttonCityList;
    private ImageButton mapButton;
    private ImageView currentWeatherImage;
    private CurrentWeather currentWeather;

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
        cityName = findViewById(R.id.cityName);
        buttonCityList = findViewById(R.id.bCityList);
        mapButton = findViewById(R.id.bMap);
        currentTemp = findViewById(R.id.currentTemp);
        currentWeatherImage = findViewById(R.id.cWeatherImage);
        currentDescription = findViewById(R.id.cDescription);

        if (extras.hasExtra("cityID")) {
            currentWeather = new CurrentWeather();
            currentWeather.city = currentWeather.getCityNameById(extras.getIntExtra("cityID", 0));
            cityName.setText(currentWeather.translateCityToRussian(currentWeather.city));
            updateInfo(String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=3007156c4d9747d2260a99b96d4841f3", currentWeather.city));
        }
        //Button listeners
        buttonCityList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cityList = new Intent(MainActivity.this, CityList.class);
                startActivity(cityList);
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
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
                    JSONObject main = response.getJSONObject("main");
                    JSONArray weather = response.getJSONArray("weather");
                    JSONObject wIcon = weather.getJSONObject(0);
                    currentWeather.temp = main.getDouble("temp");
                    currentWeather.icon = wIcon.getString("icon");
                    if(currentWeather != null) {
                        currentTemp.setText((int)currentWeather.getTempCelsius() + "\u2103");
                        currentWeatherImage.setImageResource(currentWeather.getIcon(currentWeather.icon));
                        currentDescription.setText(currentWeather.getDescription());
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