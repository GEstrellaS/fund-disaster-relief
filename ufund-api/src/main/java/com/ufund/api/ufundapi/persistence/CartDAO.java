package com.ufund.api.ufundapi.persistence;

import java.io.IOException;

import com.model.DonationCart;
import com.model.Need;

public interface CartDAO {

    DonationCart getDonationCart(String username) throws IOException;

    DonationCart addItemToDonationCart(String username, Need need) throws IOException;

    DonationCart deleteItemFromDonationCart(String username, Need need) throws IOException;

    DonationCart incrementItemInDonationCart(String username, Need need) throws IOException;

    DonationCart decrementItemInDonationCart(String username, Need need) throws IOException;
    
}
