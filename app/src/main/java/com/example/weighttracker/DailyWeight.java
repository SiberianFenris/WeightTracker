package com.example.weighttracker;

public class DailyWeight {
    private long id;
    private String date;
    private String weight;
    private int switchId;

    public DailyWeight() {
        switchId = 0;
    }

    public DailyWeight(String date, String weight) {
        //mId = id;
        this.date = date;
        this.weight = weight;
        switchId = 0;
    }
    String stringId = String.valueOf(id);
    public long getId() { return id; }
    public void setSwitchId(int id) { switchId = id; }
    public int getSwitchId() { return switchId; }
    public String getDate() { return date; }
    public String getWeight() { return weight; }
    public void setDate(String date) { this.date = date; }
    public void setWeight(String weight) { this.weight = weight; }
    public void setId(long id) { this.id = id; }
    public String getStringId() { return stringId; }
}