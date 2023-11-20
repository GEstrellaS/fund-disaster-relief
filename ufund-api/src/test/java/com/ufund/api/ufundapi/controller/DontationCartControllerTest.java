package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;


import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.model.DonationCart;
import com.model.Need;
import com.model.Users;
import com.ufund.api.ufundapi.persistence.CartDAO;
import com.ufund.api.ufundapi.persistence.NeedDAO;

public class DontationCartControllerTest {
    private DonationCartController cartController;
    private NeedController needController;
    private CartDAO mockCartDAO;
    private NeedDAO mockNeedDAO;

 
    /**
     * Before each test, create a new NeedController object and inject
     * a mock Need DAO
     * @throws IOException
     */
    @BeforeEach
    public void setupNeedController() throws IOException {
        mockCartDAO = mock(CartDAO.class);
        mockNeedDAO = mock(NeedDAO.class);
        cartController = new DonationCartController(mockCartDAO, mockNeedDAO);
    }

    @Test
    public void testgetDonationCart() throws IOException{
        String username = "_AbelGirma";
        DonationCart cart = new DonationCart();
        when(mockCartDAO.getDonationCart(username)).thenReturn(cart);
        ResponseEntity<DonationCart> response = cartController.getDonationCart(username);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    public void testgetDonationCartHandleException() throws IOException{
        String username = "_AbelGirma";
        doThrow(new IOException()).when(mockCartDAO).getDonationCart(username);
        ResponseEntity<DonationCart> response = cartController.getDonationCart(username);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testaddItemToCart() throws IOException{
        String name = "Trashcans";
        Need need = new Need("Trashcans", 6.78, 22, 30, "Household");
        when(mockNeedDAO.getNeed(name)).thenReturn(need);
        ResponseEntity<DonationCart> response = cartController.addItemToCart(name, need);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testaddItemToCartNotFound() throws IOException{
        String name = "Trashcans";
        Need need = new Need("Trashcans", 6.78, 22, 30, "Household");
        when(mockNeedDAO.getNeed(name)).thenReturn(null);
        ResponseEntity<DonationCart> response = cartController.addItemToCart(name, need);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // @Test 
    // public void testaddItemToCartHandleException() throws IOException{
    //     String usernameString = "_AbelGirma";
    //     Need need = new Need("Trashcans", 6.78, 22, 30, "Household");       
    //     doThrow(new IOException()).when(mockCartDAO).addItemToDonationCart(usernameString, need);
    //     ResponseEntity<DonationCart> response = cartController.addItemToCart(usernameString, need);
    //     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    // }

    @Test 
    public void testdeleteItemToCart() throws IOException{
        String usernameString = "_AbelGirma";
        Need need = new Need("Trashcans", 6.78, 22, 30, "Household");    
        DonationCart cart = new DonationCart();
        when(mockCartDAO.deleteItemFromDonationCart(usernameString, need)).thenReturn(cart);
        ResponseEntity<DonationCart> response = cartController.deleteItemToCart(usernameString, need);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test 
    public void testdeleteItemToCartHandleException() throws IOException{
        String usernameString = "_AbelGirma";
        Need need = new Need("Trashcans", 6.78, 22, 30, "Household");    
        doThrow(new IOException()).when(mockCartDAO).deleteItemFromDonationCart(usernameString, need);
        ResponseEntity<DonationCart> response = cartController.deleteItemToCart(usernameString, need);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testcheckout() throws IOException{
        String usernameString = "_AbelGirma";
        DonationCart cart = new DonationCart();
        when(mockCartDAO.getDonationCart(usernameString)).thenReturn(cart);
        ResponseEntity<DonationCart> response = cartController.checkout(usernameString);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testcheckoutHandleException() throws IOException{
        String usernameString = "_AbelGirma";
        doThrow(new IOException()).when(mockCartDAO).getDonationCart(usernameString);
        ResponseEntity<DonationCart> response = cartController.checkout(usernameString);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testincrementItem() throws IOException{
        String usernameString = "_AbelGirma";
        Need need = new Need("Trashcans", 6.78, 22, 30, "Household");  
        DonationCart cart = new DonationCart();
        when(mockCartDAO.incrementItemInDonationCart(usernameString, need)).thenReturn(cart);
        ResponseEntity<DonationCart> response = cartController.incrementItem(usernameString, need);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testincrementItemHandleException() throws IOException{
        String usernameString = "_AbelGirma";
        Need need = new Need("Trashcans", 6.78, 22, 30, "Household");
        doThrow(new IOException()).when(mockCartDAO).incrementItemInDonationCart(usernameString, need);
        ResponseEntity<DonationCart> response = cartController.incrementItem(usernameString, need);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    

    @Test
    public void testdecrementItem() throws IOException{
        String usernameString = "_AbelGirma";
        Need need = new Need("Trashcans", 6.78, 22, 30, "Household");  
        DonationCart cart = new DonationCart();
        when(mockCartDAO.decrementItemInDonationCart(usernameString, need)).thenReturn(cart);
        ResponseEntity<DonationCart> response = cartController.decrementItem(usernameString, need);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testdecrementItemHandleException() throws IOException{
        String usernameString = "_AbelGirma";
        Need need = new Need("Trashcans", 6.78, 22, 30, "Household");
        doThrow(new IOException()).when(mockCartDAO).decrementItemInDonationCart(usernameString, need);
        ResponseEntity<DonationCart> response = cartController.decrementItem(usernameString, need);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
