package com.example.weighttracker;

public class DailyWeight {
    private long id;
    private String date;
    private String weight;

    public DailyWeight() {
    }

    public DailyWeight(String date, String weight) {
        this.date = date;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
