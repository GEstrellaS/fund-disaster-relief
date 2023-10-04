package com.classes;

public class Users {
    private String username;
    private boolean isManager;

    public Users(String username, boolean isManager){
        this.username = username;
        this.isManager = isManager;
    }

    public String getUsername(){
        return this.username;
    }

    public boolean isManager(){
        return this.isManager;
    }
}
