package com.example.no0001.Domain.Trans;

public class UserTrans {
    private int userId;
    private String userName;
    private String location;

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

    public UserTrans() {
    }

    public UserTrans(int userId, String userName, String location) {
        this.userId = userId;
        this.userName = userName;
        this.location = location;
    }
}
