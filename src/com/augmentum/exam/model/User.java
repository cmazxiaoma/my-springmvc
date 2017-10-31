package com.augmentum.exam.model;

public class User {
    private int userId;
    private String name;
    private String password;

    public User(){}

    public User(int userId, String name, String password) {
        super();
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", password=" + password + "]";
    }
}
