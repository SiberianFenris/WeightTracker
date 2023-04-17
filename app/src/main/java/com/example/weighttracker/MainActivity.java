package com.example.weighttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WeightDB database;
    private TextView currentGoal;
    private TextView currentWeight;
    private GoalWeight goalWeight;
    private DailyWeight dailyWeight;
    private String updateTarget;
    private String updateCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        // initialize database
        database = WeightDB.getInstance(getApplicationContext());
        currentGoal = findViewById(R.id.target_weight_value);
        currentWeight = findViewById(R.id.current_weight_value);
        //obtain dynamic elements
        goalWeight = database.getGoalWeight();
        // fetches the most recent weight only
        dailyWeight = database.getSingleDailyWeight();
        // updates the text only if there is a target weight and daily weight record
        // inline toasts were for debugging - left in for further debugging
        if (goalWeight != null) {
            updateTarget = goalWeight.getWeight();
            currentGoal.setText(updateTarget);
        }
        else if (goalWeight == null) {
            Toast.makeText(getApplicationContext(), "No Goal Weight Set!", Toast.LENGTH_LONG).show();
        }
        if (dailyWeight != null) {
            updateCurrent = dailyWeight.getWeight();
            currentWeight.setText(updateCurrent);
        }
    }
    // handling for a button no longer in use - left in for debugging
    public void onClick(View view) {
        goalWeight = database.getGoalWeight();
        dailyWeight = database.getSingleDailyWeight();
        if (goalWeight != null) {
            updateTarget = goalWeight.getWeight();
            Toast.makeText(getApplicationContext(), goalWeight.getWeight(), Toast.LENGTH_LONG).show();
            currentGoal.setText(updateTarget);
        }
        else if (goalWeight == null) {
            Toast.makeText(getApplicationContext(), "No Set Target", Toast.LENGTH_LONG).show();
        }
        if (dailyWeight != null) {
            updateCurrent = dailyWeight.getWeight();
            Toast.makeText(getApplicationContext(), updateCurrent, Toast.LENGTH_LONG).show();
            currentWeight.setText(updateCurrent);
        }
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
    void openFragment(Fragment fragment) {
        FragmentTransaction newTransaction = getSupportFragmentManager().beginTransaction();
        newTransaction.add(R.id.main_view, fragment);
        newTransaction.addToBackStack(null);
        newTransaction.commit();
    }
}