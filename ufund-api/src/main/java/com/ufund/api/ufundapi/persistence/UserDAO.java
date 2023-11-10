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

    Users login(String username, String password);
}
