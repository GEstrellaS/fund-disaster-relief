package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
        
        assertEquals(expectedName,need.getName());
        assertEquals(expectedCost, need.getCost());
        assertEquals(expectedQuantity,need.getQuantity());
        assertEquals(expectedType, need.getType());
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
    public void testToString() {
        String name = "Insulin";
        double cost = 5.01;
        int quantity = 10;
        String type = "Medical Item";
        Need need = new Need(initialName, expectedCost, expectedQuantity, expectedType);
        String expectedString = String.format(Need.STRING_FORMAT, name, cost, quantity, type);

        String actualString = hero.toString();

        assertEquals(expectedString,actualString);
    }
}