package ru.geekbrains.android_1myfirstproject.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import ru.geekbrains.android_1myfirstproject.Parcel;
import ru.geekbrains.android_1myfirstproject.R;
import ru.geekbrains.android_1myfirstproject.databinding.FragmentChooseCityBinding;

public class ChooseCityFragment extends Fragment {

    private FragmentChooseCityBinding binding;
    private final String PARCEL = "parcel";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_choose_city, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AutoCompleteTextView inputCityTextView = setAutoCompleteTextView();
        initButtonListener(inputCityTextView);
    }

    private AutoCompleteTextView setAutoCompleteTextView() {
        if (getActivity() != null) {
            AutoCompleteTextView inputCityTextView = binding.accInputCity;
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.cities_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            inputCityTextView.setAdapter(adapter);
            return inputCityTextView;
        }
        return null;
    }

    private void initButtonListener(AutoCompleteTextView inputCityTextView) {
        if (getActivity() != null) {
            binding.okButtonChooseCity.setOnClickListener((view) -> {
                Parcel currentParcel = new Parcel(inputCityTextView.getText().toString());

                Snackbar.make(requireView(), R.string.snackbar__text, Snackbar.LENGTH_LONG)
                        .setAction(R.string.snackbar_action_text, view1 -> {
                            Intent intentTo1ndScreen = new Intent();
                                intentTo1ndScreen.putExtra(PARCEL, currentParcel);
                            getActivity().setResult(Activity.RESULT_OK, intentTo1ndScreen);
                            getActivity().finish();
                        }).show();
            });
        }
    }
}
