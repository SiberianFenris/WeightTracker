package com.example.weighttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class WeightHistory extends AppCompatActivity {
    private WeightDB database;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_history);
        database = WeightDB.getInstance(getApplicationContext());
        recyclerView = findViewById(R.id.weight_history_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new WeightAdapter(getWeightEntries()));
    }

    private List<WeightEntry> getWeightEntries() {
        List<DailyWeight> dailyWeights = database.getDailyWeights();
        List<WeightEntry> weightEntries = new ArrayList<>();
        for (DailyWeight dailyWeight : dailyWeights) {
            WeightEntry entry = new WeightEntry(dailyWeight.getDate(), dailyWeight.getWeight());
            weightEntries.add(entry);
        }
        return weightEntries;
    }

    // menu handling
    public void onClickHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickHistory(View view) {
        Intent intent = new Intent(this, WeightHistory.class);
        startActivity(intent);
    }

    public void onClickAddRecord(View view) {
        Intent intent = new Intent(this, AddWeight.class);
        startActivity(intent);
    }

    public void onClickWeightGoal(View view) {
        Intent intent = new Intent(this, EditGoalWeight.class);
        startActivity(intent);
    }

    public void onClickSettings(View view) {
        Intent intent = new Intent(this, AppSettings.class);
        startActivity(intent);
    }
}
