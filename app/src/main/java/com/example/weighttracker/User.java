package com.example.weighttracker;

public class User {
    private String userName;
    private long id;
    private String password;

    public User() {}

    public User (String uName, String pWord) {
        //mId = id;
        userName = uName;
        password = pWord;
    }
    public String getUser() { return userName; }
    public void setUser(String uName) { userName = uName.toLowerCase(); }
    public void setPassword(String pWord) { password = pWord; }
    public void setId(long id) { this.id = id; }
    public long getId() { return id; }
    public String getPassword() { return password; }
}
