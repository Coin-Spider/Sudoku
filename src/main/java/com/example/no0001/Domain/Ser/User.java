package com.example.no0001.Domain.Ser;

import java.sql.Timestamp;

public class User {
    private int userId;
    private String userName;
    private String location;
    private Timestamp onDate;
    private Timestamp lastTime;

    public User(int userId, String userName, String location, Timestamp onDate, Timestamp lastTime) {
        this.userId = userId;
        this.userName = userName;
        this.location = location;
        this.onDate = onDate;
        this.lastTime = lastTime;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getOnDate() {
        return onDate;
    }

    public void setOnDate(Timestamp onDate) {
        this.onDate = onDate;
    }

    public Timestamp getLastTime() {
        return lastTime;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }
}
