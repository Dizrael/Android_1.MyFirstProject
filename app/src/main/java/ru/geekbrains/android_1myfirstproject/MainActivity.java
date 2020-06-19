package ru.geekbrains.android_1myfirstproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;


import java.lang.reflect.Array;

import ru.geekbrains.android_1myfirstproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(R.layout.activity_main);

//        AutoCompleteTextView inputCityTextView = (AutoCompleteTextView) findViewById(R.id.acc_inputCity);
//        String[] cities = getResources().getStringArray(R.array.cities_array);
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(this, R.layout.activity_choose_city, R.id.acc_inputCity, cities);
//        inputCityTextView.setAdapter(adapter);

        String instanceState;
        if (savedInstanceState == null) instanceState = "Первый запуск ";
        else instanceState = "Повторный запуск ";

        Toast.makeText(getApplicationContext(), instanceState + "- onCreate()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onResume()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(getApplicationContext(), "Повторный запуск! - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Повторный запуск! - onRestoreInstanceState()");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onSaveInstanceState()");

        EditText yourFeelings = (EditText) findViewById(R.id.editTextTextPersonName);
        String saveDataFeelings = yourFeelings.getText().toString();

        saveInstanceState.putString("string",saveDataFeelings);
    }


    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onDestroy()");
    }
}