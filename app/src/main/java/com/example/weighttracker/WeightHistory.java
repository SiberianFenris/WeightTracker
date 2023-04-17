package com.example.weighttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class WeightHistory extends AppCompatActivity {
    private TextView date;
    private TextView weight;
    private TextView editDate;
    private TextView editWeight;
    private WeightDB database;
    private CheckBox box1;
    private CheckBox box2;
    private CheckBox box3;
    private CheckBox box4;
    private CheckBox box5;
    private CheckBox box6;
    private CheckBox box7;
    private CheckBox box8;
    private CheckBox box9;

    private TextView editDateTemp;
    private TextView editWeightTemp;
    private List<DailyWeight> listWeights;
    private List<DailyWeight> tempList;
    private DailyWeight dailyWeight;
    private Weight mWeightItem;
    private Button loadMore;
    private Button editButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_history);
        database = WeightDB.getInstance(getApplicationContext());
        date = findViewById(R.id.weight_history_date);
        weight = findViewById(R.id.weight_history_value);
        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);
        box5 = findViewById(R.id.box5);
        box6 = findViewById(R.id.box6);
        box7 = findViewById(R.id.box7);
        box8 = findViewById(R.id.box8);
        box9 = findViewById(R.id.box9);
        listWeights = database.getDailyWeights();
        tempList = listWeights;
        loadMore = findViewById(R.id.bttnLoadMore);
        editButton = findViewById(R.id.btnEdit);
        deleteButton = findViewById(R.id.btnDeleteEntry);
        Fragment weightItem = new Weight();
        if (listWeights.size() > 0) {
            loadMultiples(listWeights.size());
        }
    }

    void loadMultiples(int count) {
        if (count > 9) {
            loadMore.setEnabled(true);
        }
        for (int i = 0; i < count; i++) {
            FragmentTransaction insertFragment = getSupportFragmentManager().beginTransaction();
            dailyWeight = tempList.get(i);
            if (i == 0) {
                String tempString = dailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight1);
                editDateTemp = findViewById(R.id.date1);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(dailyWeight.getDate());
                box1.setEnabled(true);
            }
            else if (i == 1) {
                String tempString = dailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight2);
                editDateTemp = findViewById(R.id.date2);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(dailyWeight.getDate());
                box2.setEnabled(true);
            }
            else if (i == 2) {
                String tempString = dailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight3);
                editDateTemp = findViewById(R.id.date3);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(dailyWeight.getDate());
                box3.setEnabled(true);
            }
            else if (i == 3) {
                String tempString = dailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight4);
                editDateTemp = findViewById(R.id.date4);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(dailyWeight.getDate());
                box4.setEnabled(true);
            }
            else if (i == 4) {
                String tempString = dailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight5);
                editDateTemp = findViewById(R.id.date5);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(dailyWeight.getDate());
                box5.setEnabled(true);
            }
            else if (i == 5) {
                String tempString = dailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight6);
                editDateTemp = findViewById(R.id.date6);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(dailyWeight.getDate());
                box6.setEnabled(true);
            }
            else if (i == 6) {
                String tempString = dailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight7);
                editDateTemp = findViewById(R.id.date7);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(dailyWeight.getDate());
                box7.setEnabled(true);
            }
            else if (i == 7) {
                String tempString = dailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight8);
                editDateTemp = findViewById(R.id.date8);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(dailyWeight.getDate());
                box8.setEnabled(true);
            }
            else if (i == 8) {
                String tempString = dailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight9);
                editDateTemp = findViewById(R.id.date9);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(dailyWeight.getDate());
                box9.setEnabled(true);
            }
        }
    }

    public void loadMoreWeights(android.view.View view) {
        List<DailyWeight> subList;
        if (tempList.get(0) == listWeights.get(0)) {
            tempList = listWeights.subList(9, 17);
        }
        else if (tempList.get(0) == listWeights.get(9)) {
            tempList = listWeights.subList(18, 26);
        }
        else if (tempList.get(0) == listWeights.get(18)) {
            tempList = listWeights.subList(27, 35);
        }
    }
    // deletes selected weight
    public void deleteWeights(android.view.View view) {
        if (box1.isChecked()) {
            Toast.makeText(getApplicationContext(), "Switch 1 checked", Toast.LENGTH_SHORT).show();
            dailyWeight = listWeights.get(0);
            listWeights.remove(0);
            database.deleteDaily(dailyWeight);
        }
        if (box2.isChecked()) {
            dailyWeight = listWeights.get(1);
            listWeights.remove(1);
            database.deleteDaily(dailyWeight);
        }
        if (box3.isChecked()) {
            dailyWeight = listWeights.get(2);
            listWeights.remove(2);
            database.deleteDaily(dailyWeight);
        }
        if (box4.isChecked()) {
            dailyWeight = listWeights.get(3);
            listWeights.remove(3);
            database.deleteDaily(dailyWeight);
        }
        if (box5.isChecked()) {
            dailyWeight = listWeights.get(4);
            listWeights.remove(4);
            database.deleteDaily(dailyWeight);
        }
        if (box6.isChecked()) {
            dailyWeight = listWeights.get(5);
            listWeights.remove(5);
            database.deleteDaily(dailyWeight);
        }
        if (box7.isChecked()) {
            dailyWeight = listWeights.get(6);
            listWeights.remove(6);
            database.deleteDaily(dailyWeight);
        }
        if (box8.isChecked()) {
            dailyWeight = listWeights.get(7);
            listWeights.remove(7);
            database.deleteDaily(dailyWeight);
        }
        if (box9.isChecked()) {
            dailyWeight = listWeights.get(8);
            listWeights.remove(8);
            database.deleteDaily(dailyWeight);
        }
        // updates the list used for display after deleting item
        tempList = listWeights;
        loadMultiples(listWeights.size());
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