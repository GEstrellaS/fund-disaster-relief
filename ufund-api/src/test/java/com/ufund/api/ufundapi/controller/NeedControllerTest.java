package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.ufund.api.ufundapi.persistence.NeedDAO;
import com.model.Need;

import org.apache.tomcat.util.net.IPv6Utils;
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
    public void testGetNeedNotFound() throws Exception { 
        String needName = "Toilet Paper";
        when(mockNeedDAO.getNeed(needName)).thenReturn(null);
        ResponseEntity<Need> response = needController.getNeed(needName);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testCreateNeed() throws IOException {  
        Need need = new Need("Toilet Paper", 4.99, 24, 30, "Household");
        when(mockNeedDAO.createNeed(need)).thenReturn(need);
        ResponseEntity<Need> response = needController.createNeed(need);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(need,response.getBody());
    }

    @Test
    public void testUpdateneed() throws IOException { 

        Need existingNeed = new Need("Toilet Paper", 4.99, 24, 30, "Household");
        Need needToUpdate = new Need("Toilet Paper", 5.99, 24, 30, "Household");
        when(mockNeedDAO.getNeed(existingNeed.getName())).thenReturn(existingNeed);
        when(mockNeedDAO.updateNeed(any(Need.class))).thenReturn(needToUpdate);
        ResponseEntity<Need> response = needController.updateNeed(needToUpdate);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(needToUpdate,response.getBody());
    }

    @Test
    public void testupdateNeedHandleException() throws IOException{
        String needName = "Toilet Paper";
        Need existingNeed = new Need("Toilet Paper", 4.99, 24, 30, "Household");
        when(mockNeedDAO.getNeed(needName)).thenReturn(existingNeed);
        when(mockNeedDAO.updateNeed(existingNeed)).thenReturn(null);
        ResponseEntity<Need> response = needController.updateNeed(existingNeed);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testupdateNeedHandleException2() throws IOException{
        String needName = "Toilet Paper";
        Need existingNeed = new Need("Toilet Paper", 4.99, 24, 30, "Household");
        doThrow(new IOException()).when(mockNeedDAO).getNeed(needName);
        ResponseEntity<Need> response = needController.updateNeed(existingNeed);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    public void testGetNeeds() throws IOException { 
        Need[] Needs = new Need[2];
        Needs[0] = new Need("Toilet Paper", 4.99, 24, 30, "Household");
        Needs[1] = new Need("Trashcans", 6.78, 22, 30, "Household");
        when(mockNeedDAO.getNeeds()).thenReturn(Needs);
        ResponseEntity<Need[]> response =  needController.getNeeds();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Needs,response.getBody());
    }

    //Gonzalo Estrella
    @Test
    public void testUpdateNeedFailed() throws IOException { 
        Need need = new Need("Trashcans", 6.78, 22, 30, "Household");
        when(mockNeedDAO.updateNeed(need)).thenReturn(null);
        ResponseEntity<Need> response = needController.updateNeed(need);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    //Done by Abel Girma
    @Test
    public void testDeleteNeedNotFound() throws IOException { 
        String name = "Trashcans";
        when(mockNeedDAO.deleteNeed(name)).thenReturn(false);
        ResponseEntity<Need> response = needController.deleteNeed(name);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteNeedHandleException() throws IOException{
        String name = "Trashcans";
        doThrow(new IOException()).when(mockNeedDAO).getNeed(name);
        ResponseEntity<Need> response = needController.deleteNeed(name);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
       
    }

    @Test
    public void testDeleteNeedHandleException2() throws IOException{
        String name = "Trashcans";
        Need need = new Need("Trashcans", 6.78, 22, 30, "Household");
        when(mockNeedDAO.getNeed(name)).thenReturn(need);
        when(mockNeedDAO.deleteNeed(name)).thenReturn(false);
        ResponseEntity<Need> response = needController.deleteNeed(name);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());   
    }


    // Done by Abel Girma 
    @Test
    public void testGetNeedHandleException() throws Exception { 
        String name = "Trashcans";
        doThrow(new IOException()).when(mockNeedDAO).getNeed(name);
        ResponseEntity<Need> response = needController.getNeed(name);    
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    // Done by Abel Girma 
    @Test
    public void testSearchNeeds() throws IOException { 
        String searchString = "la";
    
        Need[] Needs = new Need[2];
        Needs[0] = new Need("Toilet Paper", 4.99, 24, 30, "Household");
        Needs[1] = new Need("Trashcans", 6.78, 22, 30, "Household");
        when(mockNeedDAO.findNeeds(searchString)).thenReturn(Needs);
        ResponseEntity<Need[]> response = needController.searchNeeds(searchString);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Needs,response.getBody());
    }
    
    @Test
    public void testSearchNeedsHandleException() throws IOException { 
        String searchString = "an";
        doThrow(new IOException()).when(mockNeedDAO).findNeeds(searchString);
        ResponseEntity<Need[]> response = needController.searchNeeds(searchString);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testSearchNeedsNotFound() throws IOException { 
        String searchString = "an";
        when(mockNeedDAO.findNeeds(searchString)).thenReturn(null);
        ResponseEntity<Need[]> response = needController.searchNeeds(searchString);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }



    @Test
    public void testDeleteNeed() throws IOException { 
        String searchString = "an";
        when(mockNeedDAO.getNeed(searchString)).thenReturn(new Need(searchString, 0, 0, 0, searchString));        
        when(mockNeedDAO.deleteNeed(searchString)).thenReturn(true);
        ResponseEntity<Need> response = needController.deleteNeed(searchString);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
    @Test
    public void testGetNeed() throws IOException {  
        Need need = new Need("Trashcans", 6.78, 22, 30, "Household");
        when(mockNeedDAO.getNeed(need.getName())).thenReturn(need);
        ResponseEntity<Need> response = needController.getNeed(need.getName());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(need,response.getBody());
    }

    @Test
    public void testCreateNeedHandleException() throws IOException { 
        Need need = new Need("Trashcans", 6.78, 22, 30, "Household");
        doThrow(new IOException()).when(mockNeedDAO).createNeed(need);
        ResponseEntity<Need> response = needController.createNeed(need);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testCreateNeedHandleException2() throws IOException { 
        Need need = new Need("Trashcans", 6.78, 22, 30, "Household");
        when(mockNeedDAO.getNeeds()).thenReturn(null);
        when(mockNeedDAO.createNeed(need)).thenReturn(null);
        ResponseEntity<Need> response = needController.createNeed(need);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }


    @Test
    public void testCreateNeedFailed() throws IOException{
        String name = "Trashcans";
        Need existingNeed = new Need("Trashcans", 6.78, 22, 30, "Household");
        when(mockNeedDAO.getNeeds()).thenReturn(new Need[]{existingNeed});
        when(mockNeedDAO.getNeed(name)).thenReturn(existingNeed);
        ResponseEntity<Need> response = needController.createNeed(existingNeed);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }


    @Test
    public void testGetNeedsHandleException() throws IOException { 
        doThrow(new IOException()).when(mockNeedDAO).getNeeds();
        ResponseEntity<Need[]> response = needController.getNeeds();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    
    @Test
    public void testGetNeedsNotFound() throws IOException { 
        String name = "Trashcans";
        when(mockNeedDAO.getNeed(name)).thenReturn(null);
        ResponseEntity<Need[]> response = needController.getNeeds();
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

}