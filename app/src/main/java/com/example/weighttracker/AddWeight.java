package com.example.weighttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddWeight extends AppCompatActivity {
    private EditText mDate;
    private EditText mWeight;
    private WeightDB mDatabase;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_weight);
        mDate = findViewById(R.id.add_date);
        mWeight = findViewById(R.id.enter_weight);
        mDatabase = WeightDB.getInstance(getApplicationContext());
        mPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);

        // Set an OnClickListener on the date field to show the DatePickerDialog
        mDate.setOnClickListener(new View.OnClickListener() {
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
                        mDate.setText(monthFormatted + "/" + dayFormatted + "/" + year);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    public void addWeight(android.view.View view) {
        // get the weight goal from shared preferences
        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        String goalWeightString = preferences.getString("goal_weight", "0");
        int goalWeight = Integer.parseInt(goalWeightString);

        String sDate = mDate.getText().toString();
        String sWeight = mWeight.getText().toString();

        // convert weight strings to integers
        int currentWeight = Integer.parseInt(sWeight);
        if (currentWeight <= goalWeight) {
            // send notification if current weight matches goal weight
            sendCongratulationsNotification();
        }

        DailyWeight dailyWeight = new DailyWeight(sDate, sWeight);
        if (mDatabase.addDailyWeight(dailyWeight)) {
            Toast.makeText(getApplicationContext(), "Added Successfully!", Toast.LENGTH_LONG).show();
        }
        else { Toast.makeText(getApplicationContext(), "An Error Occurred", Toast.LENGTH_LONG).show(); }
    }

    private void checkGoalWeightReached(String sWeight) {
        String goalWeight = mPreferences.getString("goal_weight", "");
        if (!goalWeight.isEmpty() && sWeight.equals(goalWeight)) {
            sendCongratulationsNotification();
        }
    }

    private void sendCongratulationsNotification() {
        // Get the notification manager
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Create the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.scale)
                .setContentTitle("Congratulations!")
                .setContentText("You have reached your goal weight!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        // Show the notification
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.POST_NOTIFICATIONS }, 1);
            return;
        }
        notificationManager.notify(1, builder.build());
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
