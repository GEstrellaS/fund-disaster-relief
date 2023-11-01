package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.DonationCart;
import com.model.Need;
import com.model.Users;

@Component
public class UserFileDAO implements UserDAO{

    private static final Logger LOG = Logger.getLogger(NeedFileDAO.class.getName());
    Map<String,Users> users; 
    private ObjectMapper objectMapper;
    private String filename;

    public UserFileDAO(@Value("${users.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    private boolean save() throws IOException {
        Users[] needArray = getAllUsers();
        objectMapper.writeValue(new File(filename),needArray);
        return true;
    }

    private boolean load() throws IOException {
        users = new TreeMap<>();
        Users[] usersArray = objectMapper.readValue(new File(filename),Users[].class);
        for (Users user : usersArray) {
            users.put(user.getUsername(), user);
        }
        return true;
    }

    private Users[] getAllUsers() {
        return getUsers(null);
    }

    @Override
    public Users getUser(String username) {
        synchronized(users) {
            if (users.containsKey(username))
                return users.get(username);
            else
                return null;
        }
    }

    @Override
    public Users[] getUsers(String containsText){
        ArrayList<Users> usersArrayList = new ArrayList<>();

        for (Users user : users.values()) {
            if (containsText == null || user.getUsername().contains(containsText)) {
                usersArrayList.add(user);
            }
        }

        Users[] usersArray = new Users[usersArrayList.size()];
        usersArrayList.toArray(usersArray);
        return usersArray;
    }

    @Override
    public String createUser(String username, String password) throws IOException {
        synchronized(users) {
            for (Users u : users.values()) {
                if (u.getUsername().equals(username)) {
                    return null;
                }
            }

            Users newUser = new Users(username, password, false);
            users.put(newUser.getUsername(), newUser);
            save();
            return username;
        }
    }

    @Override
    public boolean deleteUser(String username) throws IOException {
        synchronized(users) {
            if (users.containsKey(username)) {
                users.remove(username);
                return save();
            }
            else
                return false;
        }
    }

    @Override
    public DonationCart getDonationCart(String username) throws IOException {
        for(Users user: users.values()){
            if((user.getUsername()).equals(username)){
                return user.getDonationCart();
            }
        }
        return new DonationCart();
    }

    @Override
    public DonationCart addItemToDonationCart(String username, Need need) throws IOException {
        if( need == null ){
            return new DonationCart();
        }else{
            for(Users user: users.values()){
                if((user.getUsername()).equals(username)){
                    user.getDonationCart().addItem(need);
                    save();
                    return user.getDonationCart();
                }
            }
            return new DonationCart();
        }
    }

    @Override
    public DonationCart deleteItermToDonationCart(String username, Need need) throws IOException {
        if( need == null ){
            return new DonationCart();
        }else{
            for(Users user: users.values()){
                if((user.getUsername()).equals(username)){
                    user.getDonationCart().removeItem(need);
                    save();
                    return user.getDonationCart();
                }
            }
            return new DonationCart();
        }
    }

    @Override
    public Users login(String username, String password){
        for(Users user: users.values()){
            if((user.getUsername()).equals(username) && (user.getPassword()).equals(password)){
                return user;
            }
        }
        return null;
    }
}
