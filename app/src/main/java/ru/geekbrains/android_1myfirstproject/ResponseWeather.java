package ru.geekbrains.android_1myfirstproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseWeather {

    @SerializedName("main")
    @Expose
    private Main main;

    public ResponseWeather(Main main) {
        this.main = main;
    }

    public Main getMain() {
        return main;
    }

}
