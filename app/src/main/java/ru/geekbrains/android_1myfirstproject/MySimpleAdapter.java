package ru.geekbrains.android_1myfirstproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MySimpleAdapter extends RecyclerView.Adapter<MySimpleAdapter.SimpleViewHolder> {

    private ArrayList<WeatherData> dataList = new ArrayList<>();

    public void setData(List<WeatherData> list){
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimpleViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class SimpleViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDay;
        private TextView tvTemp;

        public SimpleViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDay = itemView.findViewById(R.id.day_textview);
            tvTemp = itemView.findViewById(R.id.temp_textview);
        }

        public void bind(WeatherData item){
            tvDay.setText(item.day);
            tvTemp.setText(String.valueOf(item.temp));
        }
    }

}
