package com.ufund.api.ufundapi.persistence;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


import java.io.File;
import java.io.IOException;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Need;
import com.model.Users;


@Tag("Persistence-tier")
public class UserFileDAOTest {
    UserFileDAO userFileDAO;
    Users[] testUsers;
    ObjectMapper mockObjectMapper;


    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     *
     * @throws IOException
     */
    @BeforeEach
    public void setupNeedFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testUsers = new Users[3];
        testUsers[0] = new Users("Abel", "SWENLover",false);
        testUsers[1] = new Users("Gonzalo", "CompilingGod", false);
        testUsers[2] = new Users("Ayon", "CoderForLife", false);


        when(mockObjectMapper
                .readValue(new File("doesnt_matter.txt"), Users[].class))
                .thenReturn(testUsers);
        userFileDAO = new UserFileDAO("doesnt_matter.txt", mockObjectMapper);
    }


    @Test
    public void testGetUser() {
        Users user = userFileDAO.getUser("Abel");
        assertNotNull(user);
        assertEquals("Abel", user.getUsername());
        assertEquals("SWENLover", user.getPassword());
    }
    
    @Test
    public void testDeleteUser() throws IOException {
        String existingUsername = "Abel";
        boolean deleted = userFileDAO.deleteUser(existingUsername);
        assertTrue(deleted);
        Users deletedUser = userFileDAO.getUser(existingUsername);
        assertNull(deletedUser);
    }

    @Test
    public void testCreateUser() throws IOException {
        String newUsername = "NewUser";
        String newPassword = "NewPassword";

        String newUser = userFileDAO.createUser(newUsername, newPassword);
        assertNotNull(newUser);
        assertEquals(newUsername, newUser);

        Users retrievedUser = userFileDAO.getUser(newUsername);
        assertNotNull(retrievedUser);
        assertEquals(newUsername, retrievedUser.getUsername());
        assertEquals(newPassword, retrievedUser.getPassword());
}

    



    @Test
    public void testLogin() {
        Users loggedInUser = userFileDAO.login("Abel", "SWENLover");


        assertNotNull(loggedInUser);
        assertEquals("Abel", loggedInUser.getUsername());
        assertEquals("SWENLover", loggedInUser.getPassword());
    }
}
