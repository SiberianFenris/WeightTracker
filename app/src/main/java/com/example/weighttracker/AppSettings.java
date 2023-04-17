package com.example.weighttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.widget.Toast;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class AppSettings extends AppCompatActivity {
    private Switch settingsSwitch;
    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_screen);
        settingsSwitch = findViewById(R.id.notificationSwitch);
        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        boolean notificationsEnabled = preferences.getBoolean("notifications_enabled", false);
        settingsSwitch.setChecked(notificationsEnabled);
    }
    // save notification settings
    public void settingsSave(View view) {
        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("notifications_enabled", settingsSwitch.isChecked());
        editor.apply();
        Toast.makeText(this, "Notification settings saved", Toast.LENGTH_SHORT).show();
    }

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