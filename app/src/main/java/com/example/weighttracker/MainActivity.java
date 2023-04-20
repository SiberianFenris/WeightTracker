package com.example.weighttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        // initialize database
        WeightDB.initialize(getApplicationContext());
        database = WeightDB.getInstance();
        currentGoal = findViewById(R.id.target_weight_value);
        currentWeight = findViewById(R.id.current_weight_value);
        preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", 0);
        User user = new User();
        user.setId(userId);
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
    public void onClick(View view) {
        preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", 0);
        User user = new User();
        user.setId(userId);
        goalWeight = database.getGoalWeight();
        dailyWeight = database.getSingleDailyWeight();
        if (goalWeight != null) {
            updateTarget = goalWeight.getWeight();
            Toast.makeText(getApplicationContext(), goalWeight.getWeight(), Toast.LENGTH_LONG).show();
            currentGoal.setText(updateTarget);
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
        // clear the SharedPreferences for the current user
        preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        // start the AppSettings activity
        Intent intent = new Intent(this, AppSettings.class);
        startActivity(intent);
    }

}