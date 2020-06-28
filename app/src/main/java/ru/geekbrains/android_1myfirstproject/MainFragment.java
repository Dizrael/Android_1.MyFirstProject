package ru.geekbrains.android_1myfirstproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import ru.geekbrains.android_1myfirstproject.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private static final String PARCEL = "parcel";
    private FragmentMainBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initButtonListener();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //проверяем, что рез-т пришел от активити 2 и изменяем название тулбара на выбранный
        //пользователем город
        updateCityName(requestCode, resultCode, data);
    }

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
}
