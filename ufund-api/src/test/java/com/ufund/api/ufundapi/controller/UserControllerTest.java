package com.ufund.api.ufundapi.controller;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.io.IOException;


import javax.swing.text.html.parser.Entity;


import com.ufund.api.ufundapi.persistence.NeedDAO;
import com.ufund.api.ufundapi.persistence.UserDAO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.model.DonationCart;
import com.model.Need;
import com.model.Users;


import org.apache.tomcat.jni.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.reporting.ReportEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class UserControllerTest {
    private UserController userController;
    private UserDAO mockUserDAO;
 
    /**
     * Before each test, create a new NeedController object and inject
     * a mock Need DAO
     * @throws IOException
     */
    @BeforeEach
    public void setupNeedController() throws IOException {
        mockUserDAO = mock(UserDAO.class);
        userController = new UserController(mockUserDAO);
    }

    @Test
    public void testLoginUser() throws IOException{
        String username = "_AbelGirma";
        String password = "MyStrongPassowrd";
        Users existingUser = new Users(username, password, false);
        when(mockUserDAO.login(username, password)).thenReturn(existingUser);
        ResponseEntity<Users> response = userController.loginUser(username, password);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test 
    public void testLoginUserNotFound() throws IOException{
        String username = "_AbelGirma";
        String password = "MyStrongPassowrd";
        when(mockUserDAO.login(username, password)).thenReturn(null);
        ResponseEntity<Users> response = userController.loginUser(username, password);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }


    @Test
    public void testCreateUser() throws IOException {  
        String username = "_AbelGirma";
        String password = "MyStrongPassowrd";
        when(mockUserDAO.createUser(username, password)).thenReturn(username);
        ResponseEntity<String> response = userController.createUser(username, password);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(username,response.getBody());
    }


    @Test
    public void testCreateUserHandleException() throws IOException {  
        String username = "_AbelGirma";
        String password = "MyStrongPassowrd";;


        doThrow(new IOException()).when(mockUserDAO).createUser(username, password);
        ResponseEntity<String> response = userController.createUser(username, password);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }


    @Test
    public void testCreateUserConflict() throws IOException {  
        String username = "_AbelGirma";
        String password = "MyStrongPassowrd";

        Users existingUser = new Users(username, password, false);
        when(mockUserDAO.getUser(username)).thenReturn(existingUser);
        ResponseEntity<String> response = userController.createUser(username, password);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }


    @Test
    public void testSearchUsers() throws IOException {
        String searchString = "la";
        Users[] User= new Users[1];
        User[0] = new Users(searchString, searchString, false);
        when (mockUserDAO.getUsers(searchString)).thenReturn(User);        
        ResponseEntity<Users[]> response = userController.searchUsers(searchString);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
   
    @Test
    public void testSearchUsersNotFound() throws IOException {  
        String username = "_AbelGirma";
        when(mockUserDAO.getUsers(username)).thenReturn(null);
        ResponseEntity<Users> response = userController.getUser(username);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }
   
    @Test
    public void testSearchUsersHandleException() throws IOException {
        String searchString = "la";
        Users[] User= new Users[1];
        User[0] = new Users(searchString, searchString, false);
        doThrow(new IOException()).when(mockUserDAO).getUsers(searchString);
        ResponseEntity<Users[]> response = userController.searchUsers(searchString);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }


    @Test
    public void testgetUser() throws IOException{
        String searchString = "la";
        Users user = new Users(searchString, searchString, false);
        when(mockUserDAO.getUser(searchString)).thenReturn(user);
        ResponseEntity<Users> response = userController.getUser(searchString);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }


    @Test
    public void testGetUserNotFound() throws IOException{
        String searchString = "la";
        when(mockUserDAO.getUser(searchString)).thenReturn(null);
        ResponseEntity<Users> response = userController.getUser(searchString);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    public void testgetUserHandleException()  throws IOException{
        String searchString = "la";
        Users user= new Users(searchString, searchString, false);
        doThrow(new IOException()).when(mockUserDAO).getUser(searchString);
        ResponseEntity<Users> response = userController.getUser(searchString);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }


    @Test
    public void testgetUsers() throws IOException{
        String searchString = "la";
        Users[] User= new Users[1];
        User[0] = new Users(searchString, searchString, false);
        when(mockUserDAO.getUsers(null)).thenReturn(User);


        ResponseEntity<Users[]> response = userController.getUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void testgetUsersNotFount() throws IOException{
        when(mockUserDAO.getUsers(null)).thenReturn(null);
        ResponseEntity<Users[]> response = userController.getUsers();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    public void testgetUsersHandleException()  throws IOException{
        doThrow(new IOException()).when(mockUserDAO).getUsers(null);
        ResponseEntity<Users[]> response = userController.getUsers();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }


    @Test
    public void testDeleteUser() throws IOException{
        String searchString = "la";
        Users user = new Users(searchString, searchString, false);
        when(mockUserDAO.getUser(searchString)).thenReturn(user);
        when(mockUserDAO.deleteUser(searchString)).thenReturn(true);
        ResponseEntity<Users> response = userController.deleteUser(searchString);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testDeleteUserHandleexception() throws IOException{
        String searchString = "la";
        Users user = new Users(searchString, searchString, false);
        when(mockUserDAO.getUser(searchString)).thenReturn(user);
        when(mockUserDAO.deleteUser(searchString)).thenReturn(false);
        ResponseEntity<Users> response = userController.deleteUser(searchString);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test 
    public void testDeleteUserHandleException() throws IOException{
        String searchString = "la";
        doThrow(new IOException()).when(mockUserDAO).getUser(searchString);
        ResponseEntity<Users> response = userController.deleteUser(searchString);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    public void testDeleteUserNotFound() throws IOException{
        String searchString = "la";
        when(mockUserDAO.getUser(searchString)).thenReturn(null);
        ResponseEntity<Users> response = userController.deleteUser(searchString);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
       
    @Test
    public void testUserNeedsHandleException() throws IOException {
        String searchUsername = "an";
        doThrow(new IOException()).when(mockUserDAO).getUser(searchUsername);
        ResponseEntity<Users> response = userController.getUser(searchUsername);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}

