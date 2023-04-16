package com.example.weighttracker;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DailyWeight {
    private long mId;
    private String mDate;
    private String mWeight;
    private int mSwitchId;

    public DailyWeight() {
        mSwitchId = 0;
    }

    public DailyWeight(String date, String weight) {
        //mId = id;
        mDate = date;
        mWeight = weight;
        mSwitchId = 0;
    }
    String stringId = String.valueOf(mId);
    public long getId() { return mId; }
    public void setSwitchId(int id) { mSwitchId = id; }
    public int getSwitchId() { return mSwitchId; }
    public String getDate() { return mDate; }
    public String getWeight() { return mWeight; }
    public void setDate(String date) { mDate = date; }
    public void setWeight(String weight) { mWeight = weight; }
    public void setId(long id) { mId = id; }
    public String getStringId() { return stringId; }
}