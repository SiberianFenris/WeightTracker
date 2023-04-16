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
    private WeightDB mDatabase;
    private TextView mCurrentGoal;
    private TextView mCurrentWeight;
    private GoalWeight mGoalWeight;
    private DailyWeight mDailyWeight;
    private String updateTarget;
    private String updateCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        // initialize database
        mDatabase = WeightDB.getInstance(getApplicationContext());
        mCurrentGoal = findViewById(R.id.target_weight_value);
        mCurrentWeight = findViewById(R.id.current_weight_value);
        //obtain dynamic elements
        mGoalWeight = mDatabase.getGoalWeight();
        // fetches the most recent weight only
        mDailyWeight = mDatabase.getSingleDailyWeight();
        // updates the text only if there is a target weight and daily weight record
        // inline toasts were for debugging - left in for further debugging
        if (mGoalWeight != null) {
            updateTarget = mGoalWeight.getWeight();
            //Toast.makeText(getApplicationContext(), mTargetWeight.getWeight(), Toast.LENGTH_LONG).show();
            mCurrentGoal.setText(updateTarget);
        }
        else if (mGoalWeight == null) {
            Toast.makeText(getApplicationContext(), "No Set Target", Toast.LENGTH_LONG).show();
        }
        if (mDailyWeight != null) {
            updateCurrent = mDailyWeight.getWeight();
            //Toast.makeText(getApplicationContext(), updateCurrent, Toast.LENGTH_LONG).show();
            //String sWeight = mDailyWeight.getWeight();
            mCurrentWeight.setText(updateCurrent);
        }
    }
    // handling for a button no longer in use - left in for debugging
    public void onClick(View view) {
        mGoalWeight = mDatabase.getGoalWeight();
        mDailyWeight = mDatabase.getSingleDailyWeight();
        if (mGoalWeight != null) {
            updateTarget = mGoalWeight.getWeight();
            Toast.makeText(getApplicationContext(), mGoalWeight.getWeight(), Toast.LENGTH_LONG).show();
            mCurrentGoal.setText(updateTarget);
        }
        else if (mGoalWeight == null) {
            Toast.makeText(getApplicationContext(), "No Set Target", Toast.LENGTH_LONG).show();
        }
        if (mDailyWeight != null) {
            updateCurrent = mDailyWeight.getWeight();
            Toast.makeText(getApplicationContext(), updateCurrent, Toast.LENGTH_LONG).show();
            //String sWeight = mDailyWeight.getWeight();
            mCurrentWeight.setText(updateCurrent);
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
    void openTitleBar(Fragment title_bar) {
        FragmentTransaction createTitleBar = getSupportFragmentManager().beginTransaction();
        createTitleBar.replace(R.id.title_bar, title_bar);
        createTitleBar.addToBackStack(null);
        createTitleBar.commit();
    }
}