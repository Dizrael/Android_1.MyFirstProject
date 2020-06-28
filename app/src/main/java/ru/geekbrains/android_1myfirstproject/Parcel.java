package ru.geekbrains.android_1myfirstproject;

import java.io.Serializable;

public class Parcel implements Serializable {

    private String cityName;

    public String getCity() {
        return cityName;
    }

    public Parcel (String city){
        this.cityName = city;
    }

}
