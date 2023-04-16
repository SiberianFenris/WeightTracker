package com.example.weighttracker;

public class User {
    private String mUserName;
    private long mId;
    private String mPassword;

    public User() {}

    public User (String uName, String pWord) {
        //mId = id;
        mUserName = uName;
        mPassword = pWord;
    }
    public String getUser() { return mUserName; }
    public void setUser(String uName) { mUserName = uName.toLowerCase(); }
    public void setPassword(String pWord) { mPassword = pWord; }
    public void setId(long id) { mId = id; }
    public long getId() { return mId; }
    public String getPassword() { return mPassword; }
}
