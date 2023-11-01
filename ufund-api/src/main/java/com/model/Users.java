package com.model;

/**
 * Defines the Users class.
 */
public class Users {
    private String username;
    private String password;
    private DonationCart donationCart;
    private boolean isManager;

    /**
     * Users constructor
     * @param username String
     * @param isManager bool
     */
    public Users(String username, String password, boolean isManager){
        this.username = username;
        this.password = password;
        this.donationCart = new DonationCart();
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
