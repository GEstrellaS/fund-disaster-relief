package com.ufund.api.ufundapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Users;
import com.ufund.api.ufundapi.persistence.UserDAO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("users") 
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    private UserDAO userDAO;

    
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/login")

    
	public ResponseEntity<Users> loginUser(@RequestParam("username") String username, @RequestParam("password") String password){
		LOG.info("GET /users/login?username=" + username + "&password=" + password);
		Users user = userDAO.login(username, password);
        if (user != null) {
        	return new ResponseEntity<Users>(user, HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}


    @GetMapping("/{username}")
    public ResponseEntity<Users> getUser(@PathVariable String username) {
        LOG.info("GET /users/" + username);
        try {
            Users user = userDAO.getUser(username);
            if (user != null)
                return new ResponseEntity<Users>(user,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  
    @GetMapping("")
    public ResponseEntity<Users[]> getUsers() {
        LOG.info("GET /users");

        try {
            Users[] user = userDAO.getUsers(null);
            if (user!= null)
                return new ResponseEntity<Users[]>(user,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Users[]> searchUsers(@RequestParam String username) {
        LOG.info("GET /users/?username="+username);
        try {
            Users[] users = userDAO.getUsers(username);
            if (users != null)
                return new ResponseEntity<Users[]>(users,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<String> createUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        LOG.info("POST /users?username=" + username+"&password=" +password+"");
        try {
            Users checkUser = userDAO.getUser(username);
            if(checkUser != null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            userDAO.createUser(username, password);
            return new ResponseEntity<>(username, HttpStatus.CREATED);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Users> deleteUser(@PathVariable String username) {
        LOG.info("DELETE /users/" + username);

        try {
            Users existingUsers = userDAO.getUser(username);
            if (existingUsers != null){
                boolean delete = userDAO.deleteUser(username);
                if (delete){
                  return new ResponseEntity<>(HttpStatus.OK);
                }           
                 else{
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }else {
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}