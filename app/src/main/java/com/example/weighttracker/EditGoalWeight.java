package com.example.weighttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditGoalWeight extends AppCompatActivity {
    private TextView currentGoal;
    private EditText editGoal;
    private Button submitButton;
    private WeightDB database;
    private String updateGoal;
    private GoalWeight goalWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_goal_weight);
        database = WeightDB.getInstance();
        currentGoal = findViewById(R.id.current_target);
        editGoal = findViewById(R.id.edit_goal);
        submitButton = findViewById(R.id.edit_weight_button);
        goalWeight = database.getGoalWeight();
        if (goalWeight != null) {
            updateGoal = goalWeight.getWeight();
            currentGoal.setText(updateGoal);
        }
    }
    public void onClick(android.view.View view) {
        String newWeight = editGoal.getText().toString();
        Toast.makeText(getApplicationContext(), newWeight, Toast.LENGTH_LONG).show();
        if (goalWeight != null) {
            GoalWeight updatedWeight = new GoalWeight(newWeight, goalWeight.getId());
            Toast.makeText(getApplicationContext(), "Weight Updated!", Toast.LENGTH_LONG).show();
            database.updateGoal(updatedWeight);
            currentGoal.setText(updatedWeight.getWeight());

            // update goal weight in shared preferences
            SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("goal_weight", updatedWeight.getWeight());
            editor.apply();
        }
        else {
            GoalWeight goalWeight = new GoalWeight(newWeight);
            database.addGoal(goalWeight);
            Toast.makeText(getApplicationContext(), "Goal Weight Added!", Toast.LENGTH_LONG).show();

            // update goal weight in shared preferences
            SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("goal_weight", goalWeight.getWeight());
            editor.apply();
        }
    }
    // menu handling
    public void onClickHome(View view) {
        Intent intent = new Intent(this, com.example.weighttracker.MainActivity.class);
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