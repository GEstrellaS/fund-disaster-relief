package com.ufund.api.ufundapi.model;


import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import com.model.Need;
import com.model.Users;




@Tag("Model-tier")
public class UsersTest {


    @Test
    public void testCtor(){
        String username = "_AbelGirma";
        String password = "MyStrongPassowrd";
        boolean ismanager = false;
        Users existingUser = new Users(username, password, ismanager);


        assertEquals(username, existingUser.getUsername());
        assertEquals(password, existingUser.getPassword());
        assertEquals(ismanager, existingUser.isManager());
    }


    @Test
    public void testToString(){
        String username = "_AbelGirma";
        String password = "MyStrongPassowrd";
        boolean ismanager = false;
        Users existingUser = new Users(username, password, ismanager);
        String expectedString = String.format(Users.STRING_FORMAT, username, password, ismanager);
        String actualString = existingUser.toString();
        assertEquals(expectedString, actualString);        
    }
   
}
