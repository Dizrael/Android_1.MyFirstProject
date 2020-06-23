package ru.geekbrains.android_1myfirstproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import java.lang.reflect.Array;

import ru.geekbrains.android_1myfirstproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";
    protected ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(R.layout.activity_main);

        Loging("onCreate()");

        initButtonListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Loging("onStart()");
    }

    private void initButtonListener() {
        ((Button)findViewById(R.id.chooseCityButton)).setOnClickListener((view) -> {
            Intent intentTo2ndScreen = new Intent(this, ChooseCityActivity1.class);
            startActivityForResult(intentTo2ndScreen, Constants.REQ_CODE_CITY);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Loging("onActivityResult");

        if (resultCode == Activity.RESULT_OK && requestCode == Constants.REQ_CODE_CITY && data != null) {
            Loging("Are you here?");
            androidx.appcompat.widget.Toolbar myMainToolbar = (androidx.appcompat.widget.Toolbar)findViewById(R.id.mainToolbar);
            myMainToolbar.setTitle(data.getStringExtra(Constants.CITY_NAME_AGR));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Loging("onResume()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Loging("Повторный запуск! - onRestoreInstanceState()");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Loging("onSaveInstanceState()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Loging("onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Loging("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Loging("onDestroy()");
    }

    protected void Loging(String log) {
        Toast.makeText(getApplicationContext(), log, Toast.LENGTH_SHORT).show();
        Log.d(TAG, log);
    }
}