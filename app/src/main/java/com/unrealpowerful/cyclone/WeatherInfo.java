package com.unrealpowerful.cyclone;

public class WeatherInfo {

    private String[] uaCitiesRU = new String[]{"Киев", "Харьков", "Одесса", "Днепр", "Донецк", "Запорожье", "Львов", "Кривой Рог", "Николаев", "Севастополь", "Мариуполь", "Луганск", "Винница", "Макеевка", "Симферополь", "Херсон", "Полтава", "Чернигов", "Черкассы", "Хмельницкий", "Черновцы", "Житомир", "Сумы", "Ровно", "Горловка", "Ивано-Франковск", "Каменское", "Кропивницкий", "Тернополь", "Кременчуг", "Луцк", "Белая Церковь", "Краматорск", "Мелитополь", "Керчь", "Ужгород", "Славянск", "Никополь", "Бердянск", "Алчевск", "Евпатория", "Бровары", "Павлоград", "Северодонецк"};
    private String[] uaCitiesEN = new String[]{"Kyiv", "Kharkiv", "Odesa", "Dnipro", "Donetsk", "Zaporizhia", "Lviv", "Kryvyi Rih", "Mykolayiv", "Sevastopol", "Mariupol", "Luhansk", "Vinnytsia", "Makiyivka", "Simferopol", "Kherson", "Poltava", "Chernihiv", "Cherkasy", "Khmelnytskyi", "Chernivtsi", "Zhytomyr", "Sumy", "Rivne", "Horlivka", "Ivano-Frankivsk", "Kamianske", "Kropyvnytskyi", "Ternopil", "Kremenchuk", "Lutsk", "Bila Tserkva", "Kramatorsk", "Melitopol", "Kerch", "Uzhhorod", "Sloviansk", "Nikopol", "Berdyansk", "Alchevsk", "Yevpatoriya", "Brovary", "Pavlohrad", "Syeverodonetsk"};
    public String city;
    public double temp;
    public String icon;
    public String time;

    public int getIcon(String icon) {
        if (icon.contains("01d"))
            return R.drawable.img01d;
        if (icon.contains("02d"))
            return R.drawable.img02d;
        if (icon.contains("03d"))
            return R.drawable.img03d;
        if (icon.contains("04d"))
            return R.drawable.img04d;
        if (icon.contains("09d"))
            return R.drawable.img09d;
        if (icon.contains("10d"))
            return R.drawable.img10d;
        if (icon.contains("11d"))
            return R.drawable.img11d;
        if (icon.contains("13d"))
            return R.drawable.img13d;
        if (icon.contains("50d"))
            return R.drawable.img50d;
        if (icon.contains("01n"))
            return R.drawable.img01n;
        if (icon.contains("02n"))
            return R.drawable.img02n;
        if (icon.contains("03n"))
            return R.drawable.img03n;
        if (icon.contains("04n"))
            return R.drawable.img04n;
        if (icon.contains("09n"))
            return R.drawable.img09n;
        if (icon.contains("10n"))
            return R.drawable.img10n;
        if (icon.contains("11n"))
            return R.drawable.img11n;
        if (icon.contains("13n"))
            return R.drawable.img13n;
        if (icon.contains("50n"))
            return R.drawable.img13n;
        return 0;
    }

    public String getDescription() {
        if (icon.contains("01d") || icon.contains("01n"))
            return "Чистое небо";
        if (icon.contains("02d") || icon.contains("02n"))
            return "Слегка облачно";
        if (icon.contains("03d") || icon.contains("03n"))
            return "Облачность";
        if (icon.contains("04d") || icon.contains("04n"))
            return "Переменная облачность";
        if (icon.contains("09d") || icon.contains("09n"))
            return "Несильный дождь";
        if (icon.contains("10d") || icon.contains("10n"))
            return "Дождь";
        if (icon.contains("11d") || icon.contains("11n"))
            return "Гроза";
        if (icon.contains("13d") || icon.contains("13n"))
            return "Снег";
        if (icon.contains("50d") || icon.contains("50n"))
            return "Туман";
        return "";
    }

    public double getTempCelsius() {
        return temp - 273.15;
    }
    public String getCityNameById(int id) {
        return uaCitiesEN[id];
    }
    public String translateCityToRussian(String city) {
        for(int i = 0; i < uaCitiesEN.length; i++)
        {
            if(city.contains(uaCitiesEN[i]))
                return uaCitiesRU[i];
        }
        return city;
    }
}
