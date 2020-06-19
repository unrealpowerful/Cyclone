package com.unrealpowerful.cyclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CityList extends AppCompatActivity {

    private ListView cityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String[] uaCities = new String[] {
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        cityList = (ListView)findViewById(R.id.cList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.cities, uaCities);
        cityList.setAdapter(adapter);
        cityList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(CityList.this, MainActivity.class);
                        intent.putExtra("cityID", i);
                        startActivity(intent);
                    }
                }
        );
    }
}