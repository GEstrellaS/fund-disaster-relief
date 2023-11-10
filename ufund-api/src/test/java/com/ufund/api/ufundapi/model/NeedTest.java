package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.model.Need;

//Gonzalo Estrella
@Tag("Model-tier")
public class NeedTest {
    @Test
    public void testCtor() {
        String expectedName = "Insulin";
        double expectedCost = 5.01;
        int expectedCurrentQuantity = 10;
        int expectedRequiredQuantity = 20; 
        String expectedType = "Medical Item";
        Need expectedNeed = new Need(expectedName, expectedCost, expectedCurrentQuantity, expectedRequiredQuantity, expectedType);
        
        assertEquals(expectedName, expectedNeed.getName());
        assertEquals(expectedCost, expectedNeed.getCost());
        assertEquals(expectedCurrentQuantity, expectedNeed.getCurrentQuantity());
        assertEquals(expectedRequiredQuantity, expectedNeed.getRequiredQuantity());
        assertEquals(expectedType, expectedNeed.getType());
    }

    @Test
    public void testName() {
        String initialName = "Insulin";
        double expectedCost = 5.01;
        int expectedQuantity = 10;
        int expectedRequiredQuantity = 20; 
        String expectedType = "Medical Item";
        Need expectedNeed = new Need(initialName, expectedCost, expectedQuantity, expectedRequiredQuantity, expectedType);
        
        String expectedName = "Pills";
        expectedNeed.setName(expectedName);

        assertEquals(expectedName, expectedNeed.getName());
    }

    @Test
    public void testCost() {
        String expectedName = "Insulin";
        double initialCost = 5.01;
        int expectedQuantity = 10;
        int expectedRequiredQuantity = 20;
        String expectedType = "Medical Item";
        Need expectedNeed = new Need(expectedName, initialCost, expectedQuantity, expectedRequiredQuantity, expectedType);
        
        double expectedCost = 10.02;
        expectedNeed.setCost(expectedCost);

        assertEquals(expectedCost, expectedNeed.getCost());
    }

    @Test
    public void testQuantity() {
        String expectedName = "Insulin";
        double expectedCost = 5.01;
        int initialQuantity = 10;
        int expectedRequiredQuantity = 20;
        String expectedType = "Medical Item";
        Need expectedNeed = new Need(expectedName, expectedCost, initialQuantity, expectedRequiredQuantity, expectedType);
        
        int expectedQuantity = 5;
        expectedNeed.setCurrentQuantity(expectedQuantity);

        assertEquals(expectedQuantity, expectedNeed.getCurrentQuantity());
    }

    @Test
    public void testType() {
        String expectedName = "Insulin";
        double expectedCost = 5.01;
        int expectedQuantity = 10;
        int expectedRequiredQuantity = 20;
        String initialType = "Food Item";
        Need expectedNeed = new Need(expectedName, expectedCost, expectedQuantity, expectedRequiredQuantity, initialType);
        
        String expectedType = "Medical Item";
        expectedNeed.setType(expectedType);

        assertEquals(expectedType, expectedNeed.getType());
    }

    @Test
    public void testToString() {
        String name = "Insulin";
        double cost = 5.01;
        int quantity = 10;
        int requiredQuantity = 20;
        String type = "Medical Item";
        Need need = new Need(name, cost, quantity, requiredQuantity, type);
        String expectedString = String.format(Need.STRING_FORMAT, name, cost, quantity, requiredQuantity, type);

        String actualString = need.toString();

        assertEquals(expectedString,actualString);
    }
}
