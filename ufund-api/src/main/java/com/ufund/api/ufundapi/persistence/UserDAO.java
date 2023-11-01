package com.ufund.api.ufundapi.persistence;

import java.io.IOException;

import com.model.DonationCart;
import com.model.Need;
import com.model.Users;

public interface UserDAO {

    Users getUser(String name) throws IOException;

    Users[] getUsers(String containsText) throws IOException;

    String createUser(String username, String password) throws IOException;

    boolean deleteUser(String user) throws IOException;

    DonationCart getDonationCart(String user) throws IOException;

    DonationCart addItemToDonationCart(String user, Need need) throws IOException;

    DonationCart deleteItermToDonationCart(String user, Need need) throws IOException;

    Users login(String username, String password);
}
