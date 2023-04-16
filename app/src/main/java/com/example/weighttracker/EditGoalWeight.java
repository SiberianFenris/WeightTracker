package com.example.weighttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditGoalWeight extends AppCompatActivity {
    private TextView mCurrentGoal;
    private EditText mEditGoal;
    private Button mSubmitButton;
    private WeightDB mDatabase;
    private String updateGoal;
    private GoalWeight mGoalWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_goal_weight);
        mDatabase = WeightDB.getInstance(getApplicationContext());
        mCurrentGoal = findViewById(R.id.current_target);
        mEditGoal = findViewById(R.id.edit_goal);
        mSubmitButton = findViewById(R.id.edit_weight_button);
        mGoalWeight = mDatabase.getGoalWeight();
        if (mGoalWeight != null) {
            updateGoal = mGoalWeight.getWeight();
            mCurrentGoal.setText(updateGoal);
        }
    }
    public void onClick(android.view.View view) {
        String newWeight = mEditGoal.getText().toString();
        Toast.makeText(getApplicationContext(), newWeight, Toast.LENGTH_LONG).show();
        if (mGoalWeight != null) {
            GoalWeight updatedWeight = new GoalWeight(newWeight, mGoalWeight.getId());
            Toast.makeText(getApplicationContext(), updatedWeight.getWeight(), Toast.LENGTH_LONG).show();
            mDatabase.updateGoal(updatedWeight);
            mCurrentGoal.setText(updatedWeight.getWeight());
        }
        else {
            GoalWeight goalWeight = new GoalWeight(newWeight);
            mDatabase.addGoal(goalWeight);
            Toast.makeText(getApplicationContext(), "Goal Weight Added!", Toast.LENGTH_LONG).show();
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