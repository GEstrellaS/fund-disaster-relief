package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.ufund.api.ufundapi.persistence.NeedDAO;
import com.model.Need;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the Need Controller class
 */

// Ansley Orrell
@Tag("Controller-tier")
public class NeedControllerTest {
    private NeedController needController;
    private NeedDAO mockNeedDAO;

    /**
     * Before each test, create a new NeedController object and inject
     * a mock Need DAO
     * @throws IOException
     */
    @BeforeEach
    public void setupNeedController() throws IOException {
        mockNeedDAO = mock(NeedDAO.class);
        needController = new NeedController(mockNeedDAO);
    }

    @Test
    public void testGetNeedNotFound() throws Exception { // createNeed may throw IOException
        // Setup
        String needName = "Toilet Paper";
        // When the same id is passed in, our mock Need DAO will return null, simulating
        // no Need found
        when(mockNeedDAO.getNeed(needName)).thenReturn(null);

        // Invoke
        ResponseEntity<Need> response = needController.getNeed(needName);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testCreateNeed() throws IOException {  // createNeed may throw IOException
        // Setup
        Need need = new Need("Toilet Paper", 4.99, 24, "Household");
        // when createNeed is called, return true simulating successful
        // creation and save
        when(mockNeedDAO.createNeed(need)).thenReturn(need);

        // Invoke
        ResponseEntity<Need> response = needController.createNeed(need);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(need,response.getBody());
    }

    @Test
    public void testUpdateneed() throws IOException { // updateneed may throw IOException
        // Setup
        Need existingNeed = new Need("Toilet Paper", 4.99, 24, "Household");
        Need needToUpdate = new Need("Toilet Paper", 5.99, 24, "Household");
        // when updateneed is called, return true simulating successful
        // update and save

        when(mockNeedDAO.getNeed(existingNeed.getName())).thenReturn(existingNeed);
        when(mockNeedDAO.updateNeed(any(Need.class))).thenReturn(needToUpdate);

        // Invoke 
        ResponseEntity<Need> response = needController.updateNeed(needToUpdate);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(needToUpdate,response.getBody());
    }

    @Test
    public void testGetNeeds() throws IOException { // getNeeds may throw IOException
        // Setup
        Need[] Needs = new Need[2];
        Needs[0] = new Need("Toilet Paper", 4.99, 24, "Household");
        Needs[1] = new Need("Trashcans", 6.78, 22, "Household");
        // When getNeeds is called return the Needs created above
        when(mockNeedDAO.getNeeds()).thenReturn(Needs);

        // Invoke
        ResponseEntity<Need[]> response =  needController.getNeeds();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Needs,response.getBody());
    }
}