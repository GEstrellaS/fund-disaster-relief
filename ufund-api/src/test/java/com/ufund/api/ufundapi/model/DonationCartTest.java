package com.ufund.api.ufundapi.model;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import com.model.DonationCart;
import com.model.Need;


@Tag("Model-tier")
public class DonationCartTest {


    @Test
    public void testaddItem(){
        DonationCart donationCart = new DonationCart();
        Need item = new Need("Item1", 5.0, 10, 20, "Type1");
        donationCart.addItem(item);
        assertEquals(item, donationCart.getItemsInDonationCart()[0]);


    }


    @Test
    public void testincrementItem(){
        DonationCart donationCart = new DonationCart();
        Need item = new Need("Item1", 5.0, 10, 20, "Type1");
        donationCart.addItem(item);
        donationCart.incrementItem(item, 5);
        assertEquals(15 , item.getCurrentQuantity());


    }


    @Test
    public void testdecrementItem(){
        DonationCart donationCart = new DonationCart();
        Need item = new Need("Item1", 5.0, 10, 20, "Type1");
        donationCart.addItem(item);
        donationCart.decrementItem(item, 5);
        assertEquals(5 , item.getCurrentQuantity());
    }
    @Test
    public void testdecrementItemRemove(){
        DonationCart donationCart = new DonationCart();
        Need item = new Need("Item1", 5.0, 5, 20, "Type1");
        donationCart.addItem(item);
        donationCart.decrementItem(item, 5);
        assertEquals(0 , donationCart.getItemsInDonationCart().length);
    }


    @Test
    public void testremoveItem(){
        DonationCart donationCart = new DonationCart();
        Need item = new Need("Item1", 5.0, 10, 20, "Type1");
        donationCart.addItem(item);
        assertEquals(1, donationCart.getItemsInDonationCart().length);
        donationCart.removeItem(item);
        assertEquals(0, donationCart.getItemsInDonationCart().length);
    }


    @Test
    public void testgetItemsInDonationCart() throws Exception{
        DonationCart donationCart = new DonationCart();
        Need item = new Need("Item1", 5.0, 10, 20, "Type1");
        Need item2 = new Need("Item2", 7.5, 15, 25, "Type2");
        donationCart.addItem(item2);
        donationCart.addItem(item);
        assertEquals(2, donationCart.getItemsInDonationCart().length);
        assertArrayEquals(new Need[] { item2, item }, donationCart.getItemsInDonationCart());
    }
}
