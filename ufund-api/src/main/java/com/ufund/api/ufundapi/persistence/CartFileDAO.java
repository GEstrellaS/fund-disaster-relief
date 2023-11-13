package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.DonationCart;
import com.model.Need;
import com.model.Users;

@Component
public class CartFileDAO implements CartDAO {

    HashMap<String, DonationCart> usernameToCartMap;
    ObjectMapper objectMapper; 
    String filename; 

    /**
     * Creates a Need File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public CartFileDAO(@Value("${carts.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load(); 
    }

    @Override
    public DonationCart getDonationCart(String username) throws IOException {
        return usernameToCartMap.get(username);
    }

    @Override
    public DonationCart addItemToDonationCart(String username, Need need) throws IOException {
        DonationCart donationCart = getDonationCart(username);
        if (donationCart == null) {
            donationCart = new DonationCart();
            usernameToCartMap.put(username, donationCart);
        }
        if (need != null) {
            // Set the quantity to 1 when adding the item
            need.setCurrentQuantity(1);
            donationCart.addItem(need);
            save();
        }
        return donationCart;
    }
    

    @Override
    public DonationCart deleteItemFromDonationCart(String username, Need need) throws IOException {
        DonationCart donationCart = getDonationCart(username);
        if (donationCart == null){
            donationCart = new DonationCart();
            usernameToCartMap.put(username, donationCart);
        }
        if( need != null ){
            donationCart.removeItem(need);
            save();
        }
        return donationCart; 
    }

    @Override
    public DonationCart incrementItemInDonationCart(String username, Need need) throws IOException {
        DonationCart donationCart = getDonationCart(username);
        if (donationCart == null) {
            donationCart = new DonationCart();
            usernameToCartMap.put(username, donationCart);
        }
        if (need != null) {
            donationCart.incrementItem(need, 1);
            save();
        }
        return donationCart;
    }

    @Override
    public DonationCart decrementItemInDonationCart(String username, Need need) throws IOException {
    DonationCart donationCart = getDonationCart(username);
    if (need != null) {
        donationCart.decrementItem(need, 1);
        save();
    }
    return donationCart;
    }


    private void load() throws IOException{
        TypeReference<HashMap<String,DonationCart>> typeRef = new TypeReference<HashMap<String,DonationCart>>() {};
        usernameToCartMap = objectMapper.readValue(new File(filename), typeRef);
    }

    private void save() throws IOException {
        objectMapper.writeValue(new File(filename),usernameToCartMap);
    }

}
