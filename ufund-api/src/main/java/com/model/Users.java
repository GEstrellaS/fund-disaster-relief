package com.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines the Users class.
 */
public class Users {
    private static final Logger LOG = Logger.getLogger(Need.class.getName());
    // public final static String STRING_FORMAT = "User [username=%s, password=%s, donationCart=%s, manager=%b]";
    public final static String STRING_FORMAT = "User [username=%s, password=%s, manager=%b]";
    @JsonProperty("username") private String username;
    @JsonProperty("password") private String password;
    // @JsonProperty("donationCart") private DonationCart donationCart;
    @JsonProperty("isManager") private boolean isManager;

    /**
     * Users constructor
     * @param username String
     * @param isManager bool
     */
    public Users(@JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("isManager") boolean isManager){
        this.username = username;
        this.password = password;
        // this.donationCart = new DonationCart();
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
     * gets the password
     * @return
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * gets the donationCart
     * @return donationCart
     */
    // public DonationCart getDonationCart(){
    //     return this.donationCart;
    // }

    /**
     * checks if the user is a Manager
     * @return true if the user is a manager, false if the user is not
     */
    public boolean isManager(){
        return this.isManager;
    }

    /**
     * overrides the default toString and implements a new string format for User
     * @return string
     */
    @Override
    public String toString(){
        return String.format(STRING_FORMAT, this.username, this.password, /*this.donationCart.getDonationCart().toString()*/ this.isManager);
    }


}
