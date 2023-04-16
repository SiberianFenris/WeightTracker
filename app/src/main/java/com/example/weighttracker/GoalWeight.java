package com.example.weighttracker;

public class GoalWeight {
    private long id;
    private String goalWeight;
    public GoalWeight(String weight, long id) {
        this.id = id;
        goalWeight = weight;
    }

    public GoalWeight(String weight) {
        this.id = -1;
        goalWeight = weight;
    }

    public GoalWeight(GoalWeight gw, long id){
        this(gw.getWeight(), id);
    }

    public String getWeight() {
        return goalWeight;
    }
    public long getId() {
        return id;
    }
}