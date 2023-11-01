package com.model;

import java.util.ArrayList;

public class DonationCart {
    private ArrayList<Need> donationCart;

    public DonationCart(){
        donationCart = new ArrayList<Need>();
    }

    public void addItem(Need item){
        this.donationCart.add(item);
    }

    public void removeItem(Need item){
        this.donationCart.remove(item);
    }
}
