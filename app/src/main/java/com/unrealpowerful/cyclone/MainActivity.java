package com.unrealpowerful.cyclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button buttonCityList;
    private Button mapButton;
    private TextView cityName;
    private TextView currentTemp;
    private double cTemp;
    final String[] uaCitiesRU = new String[] {
            "Киев",
            "Харьков",
            "Одесса",
            "Днепр",
            "Донецк",
            "Запорожье",
            "Львов",
            "Кривой Рог",
            "Николаев",
            "Севастополь",
            "Мариуполь",
            "Луганск",
            "Винница",
            "Макеевка",
            "Симферополь",
            "Херсон",
            "Полтава",
            "Чернигов",
            "Черкассы",
            "Хмельницкий",
            "Черновцы",
            "Житомир",
            "Сумы",
            "Ровно",
            "Горловка",
            "Ивано-Франковск",
            "Каменское",
            "Кропивницкий",
            "Тернополь",
            "Кременчуг",
            "Луцк",
            "Белая Церковь",
            "Краматорск",
            "Мелитополь",
            "Керчь",
            "Ужгород",
            "Славянск",
            "Никополь",
            "Бердянск",
            "Алчевск",
            "Евпатория",
            "Бровары",
            "Павлоград",
            "Северодонецк"
    };
    final String[] uaCitiesEN = new String[] {
            "Kyiv",
            "Kharkiv",
            "Odesa",
            "Dnipro",
            "Donetsk",
            "Zaporizhia",
            "Lviv",
            "Kryvyi Rih",
            "Mykolayiv",
            "Sevastopol",
            "Mariupol",
            "Luhansk",
            "Vinnytsia",
            "Makiyivka",
            "Simferopol",
            "Kherson",
            "Poltava",
            "Chernihiv",
            "Cherkasy",
            "Khmelnytskyi",
            "Chernivtsi",
            "Zhytomyr",
            "Sumy",
            "Rivne",
            "Horlivka",
            "Ivano-Frankivsk",
            "Kamianske",
            "Kropyvnytskyi",
            "Ternopil",
            "Kremenchuk",
            "Lutsk",
            "Bila Tserkva",
            "Kramatorsk",
            "Melitopol",
            "Kerch",
            "Uzhhorod",
            "Sloviansk",
            "Nikopol",
            "Berdyansk",
            "Alchevsk",
            "Yevpatoriya",
            "Brovary",
            "Pavlohrad",
            "Syeverodonetsk"
    };
    protected void doGetRequest(String url)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject currentWeather = response.getJSONObject("main");
                    cTemp = currentWeather.getDouble("temp") - 273.15;
                    currentTemp.setText(String.valueOf((int)cTemp) + "\u2103");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                currentTemp.setText(error.toString());
            }
        });
        queue.add(request);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent extras = getIntent();
        cityName = findViewById(R.id.cityName);
        buttonCityList = findViewById(R.id.bCityList);
        mapButton = findViewById(R.id.bMap);
        currentTemp = findViewById(R.id.currentTemp);

        if(extras.hasExtra("cityID")) {
            cityName.setText(uaCitiesRU[extras.getIntExtra("cityID", 0)]);
            doGetRequest(String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=3007156c4d9747d2260a99b96d4841f3", uaCitiesEN[extras.getIntExtra("cityID", 0)]));
        }
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
}