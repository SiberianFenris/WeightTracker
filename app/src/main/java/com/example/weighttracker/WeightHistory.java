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
    private TextView mDate;
    private TextView mWeight;
    private TextView editDate;
    private TextView editWeight;
    private WeightDB mDatabase;
    private CheckBox mBox1;
    private CheckBox mBox2;
    private CheckBox mBox3;
    private CheckBox mBox4;
    private CheckBox mBox5;
    private CheckBox mBox6;
    private CheckBox mBox7;
    private CheckBox mBox8;
    private CheckBox mBox9;

    private TextView editDateTemp;
    private TextView editWeightTemp;
    private List<DailyWeight> mListWeights;
    private List<DailyWeight> mTempList;
    private DailyWeight mDailyWeight;
    private Weight mWeightItem;
    private Button mLoadMore;
    private Button mEdit;
    private Button mDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_history);
        mDatabase = WeightDB.getInstance(getApplicationContext());
        mDate = findViewById(R.id.weight_history_date);
        mWeight = findViewById(R.id.weight_history_value);
        mBox1 = findViewById(R.id.box1);
        mBox2 = findViewById(R.id.box2);
        mBox3 = findViewById(R.id.box3);
        mBox4 = findViewById(R.id.box4);
        mBox5 = findViewById(R.id.box5);
        mBox6 = findViewById(R.id.box6);
        mBox7 = findViewById(R.id.box7);
        mBox8 = findViewById(R.id.box8);
        mBox9 = findViewById(R.id.box9);
        mListWeights = mDatabase.getDailyWeights();
        mTempList = mListWeights;
        mLoadMore = findViewById(R.id.bttnLoadMore);
        mEdit = findViewById(R.id.btnEdit);
        mDelete = findViewById(R.id.btnDeleteEntry);
        Fragment weightItem = new Weight();
        if (mListWeights.size() > 0) {
            loadMultiples(mListWeights.size());
        }
    }

    void loadMultiples(int count) {
        if (count > 9) {
            mLoadMore.setEnabled(true);
        }
        for (int i = 0; i < count; i++) {
            FragmentTransaction insertFragment = getSupportFragmentManager().beginTransaction();
            mDailyWeight = mTempList.get(i);
            if (i == 0) {
                String tempString = mDailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight1);
                editDateTemp = findViewById(R.id.date1);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(mDailyWeight.getDate());
                mBox1.setEnabled(true);
            }
            else if (i == 1) {
                String tempString = mDailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight2);
                editDateTemp = findViewById(R.id.date2);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(mDailyWeight.getDate());
                mBox2.setEnabled(true);
            }
            else if (i == 2) {
                String tempString = mDailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight3);
                editDateTemp = findViewById(R.id.date3);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(mDailyWeight.getDate());
                mBox3.setEnabled(true);
            }
            else if (i == 3) {
                String tempString = mDailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight4);
                editDateTemp = findViewById(R.id.date4);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(mDailyWeight.getDate());
                mBox4.setEnabled(true);
            }
            else if (i == 4) {
                String tempString = mDailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight5);
                editDateTemp = findViewById(R.id.date5);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(mDailyWeight.getDate());
                mBox5.setEnabled(true);
            }
            else if (i == 5) {
                String tempString = mDailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight6);
                editDateTemp = findViewById(R.id.date6);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(mDailyWeight.getDate());
                mBox6.setEnabled(true);
            }
            else if (i == 6) {
                String tempString = mDailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight7);
                editDateTemp = findViewById(R.id.date7);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(mDailyWeight.getDate());
                mBox7.setEnabled(true);
            }
            else if (i == 7) {
                String tempString = mDailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight8);
                editDateTemp = findViewById(R.id.date8);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(mDailyWeight.getDate());
                mBox8.setEnabled(true);
            }
            else if (i == 8) {
                String tempString = mDailyWeight.getWeight() + " lbs";
                editWeightTemp = findViewById(R.id.weight9);
                editDateTemp = findViewById(R.id.date9);
                editWeightTemp.setText(tempString);
                editDateTemp.setText(mDailyWeight.getDate());
                mBox9.setEnabled(true);
            }
        }
    }

    public void loadMoreWeights(android.view.View view) {
        List<DailyWeight> subList;
        if (mTempList.get(0) == mListWeights.get(0)) {
            mTempList = mListWeights.subList(9, 17);
        }
        else if (mTempList.get(0) == mListWeights.get(9)) {
            mTempList = mListWeights.subList(18, 26);
        }
        else if (mTempList.get(0) == mListWeights.get(18)) {
            mTempList = mListWeights.subList(27, 35);
        }
    }
    // deletes selected weight
    public void deleteWeights(android.view.View view) {
        if (mBox1.isChecked()) {
            Toast.makeText(getApplicationContext(), "Switch 1 checked", Toast.LENGTH_SHORT).show();
            mDailyWeight = mListWeights.get(0);
            mListWeights.remove(0);
            mDatabase.deleteDaily(mDailyWeight);
        }
        if (mBox2.isChecked()) {
            mDailyWeight = mListWeights.get(1);
            mListWeights.remove(1);
            mDatabase.deleteDaily(mDailyWeight);
        }
        if (mBox3.isChecked()) {
            mDailyWeight = mListWeights.get(2);
            mListWeights.remove(2);
            mDatabase.deleteDaily(mDailyWeight);
        }
        if (mBox4.isChecked()) {
            mDailyWeight = mListWeights.get(3);
            mListWeights.remove(3);
            mDatabase.deleteDaily(mDailyWeight);
        }
        if (mBox5.isChecked()) {
            mDailyWeight = mListWeights.get(4);
            mListWeights.remove(4);
            mDatabase.deleteDaily(mDailyWeight);
        }
        if (mBox6.isChecked()) {
            mDailyWeight = mListWeights.get(5);
            mListWeights.remove(5);
            mDatabase.deleteDaily(mDailyWeight);
        }
        if (mBox7.isChecked()) {
            mDailyWeight = mListWeights.get(6);
            mListWeights.remove(6);
            mDatabase.deleteDaily(mDailyWeight);
        }
        if (mBox8.isChecked()) {
            mDailyWeight = mListWeights.get(7);
            mListWeights.remove(7);
            mDatabase.deleteDaily(mDailyWeight);
        }
        if (mBox9.isChecked()) {
            mDailyWeight = mListWeights.get(8);
            mListWeights.remove(8);
            mDatabase.deleteDaily(mDailyWeight);
        }
        // updates the list used for display after deleting item
        mTempList = mListWeights;
        loadMultiples(mListWeights.size());
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