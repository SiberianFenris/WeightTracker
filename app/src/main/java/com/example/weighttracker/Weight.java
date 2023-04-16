package com.example.weighttracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Weight#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Weight extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView sourceDate;
    private TextView sourceWeight;

    // TODO: Rename and change types of parameters
    private String mDate;
    private String mWeight;
    private String nDate;
    private String nWeight;


    public Weight() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param date Parameter 1.
     * @param weight Parameter 2.
     * @return A new instance of fragment weight_item.
     */
    // TODO: Rename and change types and number of parameters
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
            mDate = getArguments().getString("date");
            mWeight = getArguments().getString("weight");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weight, container, false);
        TextView date = v.findViewById(R.id.weight_history_date);
        TextView weight = v.findViewById(R.id.weight_history_value);

        date.setText(mDate);
        weight.setText(mWeight);
        // Inflate the layout for this fragment
        return v;
    }
}