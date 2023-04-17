package com.example.weighttracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Weight extends Fragment {

    private String date;
    private String weight;


    public Weight() {
        // Required empty public constructor
    }
    public static Weight newInstance(String date, String weight) {
        Weight fragment = new Weight();
        Bundle args = new Bundle();
        args.putString("date", date);
        args.putString("weight", weight);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date = getArguments().getString("date");
            weight = getArguments().getString("weight");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weight, container, false);
        TextView date = v.findViewById(R.id.weight_history_date);
        TextView weight = v.findViewById(R.id.weight_history_value);

        date.setText(this.date);
        weight.setText(this.weight);
        return v;
    }
}