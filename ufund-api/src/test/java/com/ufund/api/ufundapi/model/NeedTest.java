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
        int expectedQuantity = 10;
        String expectedType = "Medical Item";
        Need expectedNeed = new Need(expectedName, expectedCost, expectedQuantity, expectedType);
        
        assertEquals(expectedName, expectedNeed.getName());
        assertEquals(expectedCost, expectedNeed.getCost());
        assertEquals(expectedQuantity, expectedNeed.getQuantity());
        assertEquals(expectedType, expectedNeed.getType());
    }

    @Test
    public void testName() {
        String initialName = "Insulin";
        double expectedCost = 5.01;
        int expectedQuantity = 10;
        String expectedType = "Medical Item";
        Need expectedNeed = new Need(initialName, expectedCost, expectedQuantity, expectedType);
        
        String expectedName = "Pills";
        expectedNeed.setName(expectedName);

        assertEquals(expectedName, expectedNeed.getName());
    }

    @Test
    public void testCost() {
        String expectedName = "Insulin";
        double initialCost = 5.01;
        int expectedQuantity = 10;
        String expectedType = "Medical Item";
        Need expectedNeed = new Need(expectedName, initialCost, expectedQuantity, expectedType);
        
        double expectedCost = 10.02;
        expectedNeed.setCost(expectedCost);

        assertEquals(expectedCost, expectedNeed.getCost());
    }

    @Test
    public void testQuantity() {
        String expectedName = "Insulin";
        double expectedCost = 5.01;
        int initialQuantity = 10;
        String expectedType = "Medical Item";
        Need expectedNeed = new Need(expectedName, expectedCost, initialQuantity, expectedType);
        
        int expectedQuantity = 5;
        expectedNeed.setQuantity(expectedQuantity);

        assertEquals(expectedQuantity, expectedNeed.getQuantity());
    }

    @Test
    public void testType() {
        String expectedName = "Insulin";
        double expectedCost = 5.01;
        int expectedQuantity = 10;
        String initialType = "Food Item";
        Need expectedNeed = new Need(expectedName, expectedCost, expectedQuantity, initialType);
        
        String expectedType = "Medical Item";
        expectedNeed.setType(expectedType);

        assertEquals(expectedType, expectedNeed.getType());
    }

    @Test
    public void testToString() {
        String name = "Insulin";
        double cost = 5.01;
        int quantity = 10;
        String type = "Medical Item";
        Need need = new Need(name, cost, quantity, type);
        String expectedString = String.format(Need.STRING_FORMAT, name, cost, quantity, type);

        String actualString = need.toString();

        assertEquals(expectedString,actualString);
    }
}
