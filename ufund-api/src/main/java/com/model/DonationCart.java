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

    public void incrementItem(Need item, int quantity) {
        for (Need cartItem : donationCart) {
            if (cartItem.getName().equals(item.getName())) {
                cartItem.setCurrentQuantity(cartItem.getCurrentQuantity() + quantity);
                return;
            }
        }
    }

    public void decrementItem(Need item, int quantity) {
        for (Need cartItem : donationCart) {
            if (cartItem.getName().equals(item.getName())) {
                int newQuantity = cartItem.getCurrentQuantity() - quantity;
                if (newQuantity <= 0) {
                    donationCart.remove(cartItem);
                } else {
                    cartItem.setCurrentQuantity(newQuantity);
                }
                return;
            }
        }
    }

    public void removeItem(Need item){
        this.donationCart.remove(item);
    }

    public Need[] getItemsInDonationCart(){
        Need[] list = new Need[this.donationCart.size()];
        list = this.donationCart.toArray(list);
        return list;
    }
}
