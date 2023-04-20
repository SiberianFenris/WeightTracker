package com.example.weighttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddWeight extends AppCompatActivity {
    private EditText date;
    private EditText weight;
    private WeightDB database;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_weight);
        date = findViewById(R.id.add_date);
        weight = findViewById(R.id.enter_weight);
        database = WeightDB.getInstance();
        preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);

        // Set an OnClickListener on the date field to show the DatePickerDialog
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDatePicker(view);
            }
        });
    }

    public void showDatePicker(View view) {
        // Get the current date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog and show it
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // Set the selected date in the date EditText field with 2 digits for month, day, and year
                        String monthFormatted = String.format("%02d", (month + 1));
                        String dayFormatted = String.format("%02d", day);
                        date.setText(monthFormatted + "/" + dayFormatted + "/" + year);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    public void addWeight(View view) {
        // get the user id and user object from shared preferences
        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        int userId = preferences.getInt("user_id", 0);
        User user = new User();
        user.setId(userId);

        // get the weight goal and notification settings from shared preferences
        String goalWeightString = preferences.getString("goal_weight", "0");
        int goalWeight = Integer.parseInt(goalWeightString);
        boolean enableNotifications = preferences.getBoolean("enable_notifications", true);

        String sDate = date.getText().toString();
        String sWeight = weight.getText().toString();

        if (sDate.isEmpty() || sWeight.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter a date and weight", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            // convert weight strings to integers
            int currentWeight = Integer.parseInt(sWeight);

            if (currentWeight <= goalWeight && enableNotifications) {
                // congratulate the user if current weight matches goal weight and notifications are enabled
                Toast.makeText(getApplicationContext(), "Congratulations on reaching your goal weight!", Toast.LENGTH_LONG).show();
            }

            // Store the user's daily weight data with their user ID as a key, but only if notifications are enabled
            if (enableNotifications) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("weight_" + userId, sWeight);
                editor.apply();
            }

            DailyWeight dailyWeight = new DailyWeight(sDate, sWeight);

            // Call the addDailyWeight method in the WeightDB class to add the daily weight data to the database
            if (database.addDailyWeight(dailyWeight, user)) {
                Toast.makeText(getApplicationContext(), "Added Successfully!", Toast.LENGTH_LONG).show();
                // update shared preferences with the new weight data
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("weight_" + userId, sWeight);
                editor.apply();
            }
            else {
                Toast.makeText(getApplicationContext(), "Duplicate entry!", Toast.LENGTH_LONG).show();
            }

        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid weight", Toast.LENGTH_LONG).show();
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
}
