package com.classes;

/**
 * Defines the Users class.
 */
public class Users {
    private String username;
    private boolean isManager;

    /**
     * Users constructor
     * @param username String
     * @param isManager bool
     */
    public Users(String username, boolean isManager){
        this.username = username;
        this.isManager = isManager;
    }

    /**
     * gets the username
     * @return username string
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * checks if the user is a Manager
     * @return true if the user is a manager, false if the user is not
     */
    public boolean isManager(){
        return this.isManager;
    }
}
