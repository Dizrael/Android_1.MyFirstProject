package ru.geekbrains.android_1myfirstproject.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.android_1myfirstproject.Constants;
import ru.geekbrains.android_1myfirstproject.InternetConnector;
import ru.geekbrains.android_1myfirstproject.MySimpleAdapter;
import ru.geekbrains.android_1myfirstproject.Parcel;
import ru.geekbrains.android_1myfirstproject.R;
import ru.geekbrains.android_1myfirstproject.ResponseWeather;
import ru.geekbrains.android_1myfirstproject.WeatherData;
import ru.geekbrains.android_1myfirstproject.activities.ChooseCityActivity;
import ru.geekbrains.android_1myfirstproject.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private static final String PARCEL = "parcel";
    private FragmentMainBinding binding;
    private Handler handler = new Handler();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
    }

    private void initRecyclerView() {
        List<WeatherData> dataList = initDataList();
        MySimpleAdapter adapter = new MySimpleAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        adapter.setData(dataList);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initButtonListener();

        initRefreshButtonListener();
    }


    private List<WeatherData> initDataList() {
        List<WeatherData> dataList = new ArrayList<>();
        String[] days;
        String[] temp;
        days = getResources().getStringArray(R.array.days);
        temp = getResources().getStringArray(R.array.temp);
        for (int i = 0; i < days.length; i++) {
            dataList.add(new WeatherData(days[i], temp[i]));
        }
        return dataList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //проверяем, что рез-т пришел от активити 2 и изменяем название тулбара на выбранный
        //пользователем город
        updateCityName(requestCode, resultCode, data);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateCityName(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.REQ_CODE_CITY && data != null) {
            androidx.appcompat.widget.Toolbar myMainToolbar = binding.mainToolbar;
            Parcel parcel = (Parcel) data.getSerializableExtra(PARCEL);
            assert parcel != null;
            String newTitle = parcel.getCity();
            myMainToolbar.setTitle(newTitle);
        }


    }

    private void initButtonListener() {

        //вешаем на кнопку листнер, который будет перебрасывать юзера на экран выбора города
        binding.chooseCityButton.setOnClickListener((view) -> {

            Intent intentTo2ndScreen = new Intent(getActivity(), ChooseCityActivity.class);
            startActivityForResult(intentTo2ndScreen, Constants.REQ_CODE_CITY);

        });
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initRefreshButtonListener() {
        binding.refreshButton.setOnClickListener((view) -> {
            MaterialTextView temp = binding.temp;
            MaterialTextView pressure = binding.pressure;
            MaterialTextView humidity = binding.humidity1;

            new Thread(() -> {
                try {
                    InternetConnector connector = new InternetConnector(String.valueOf(binding.mainToolbar.getTitle()));
                    String result = connector.getWeatherData();
                    Gson gson = new Gson();
                    ResponseWeather resultWeather = gson.fromJson(result, ResponseWeather.class);

                    handler.post(() -> {
                        temp.setText("Температура: " + resultWeather.getMain().getTemp());
                        pressure.setText("Давление: " + (Math.round(resultWeather.getMain().getPressure() / 1.333)) + " мм. рт. стлб.");
                        humidity.setText("Влажность: " + resultWeather.getMain().getHumidity() + "%");
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }
}
