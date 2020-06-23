package ru.geekbrains.android_1myfirstproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.*;

import ru.geekbrains.android_1myfirstproject.databinding.ActivityChooseCity1Binding;

public class ChooseCityActivity1 extends AppCompatActivity {

    protected ActivityChooseCity1Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseCity1Binding.inflate(LayoutInflater.from(this));
        setContentView(R.layout.activity_choose_city1);

        AutoCompleteTextView inputCityTextView = setAutoCompleteTextView();

        initButtonListener(inputCityTextView);
    }

    private void initButtonListener(AutoCompleteTextView inputCityTextView) {
        ((Button)findViewById(R.id.OkButtonChooseCity)).setOnClickListener((view) -> {
            String cityName = inputCityTextView.getText().toString();

            Intent intentTo1ndScreen = new Intent();
            intentTo1ndScreen.putExtra(Constants.CITY_NAME_AGR, cityName);

            setResult(Activity.RESULT_OK, intentTo1ndScreen);
            finish();
        });
    }

    private AutoCompleteTextView setAutoCompleteTextView() {
        AutoCompleteTextView inputCityTextView = findViewById(R.id.acc_inputCity);
        String[] cities = getResources().getStringArray(R.array.cities_array);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, R.layout.activity_choose_city1, R.id.acc_inputCity, cities);
        inputCityTextView.setAdapter(adapter);
        return inputCityTextView;
    }

}