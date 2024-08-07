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

@Tag("Persistence-tier")
public class NeedFileDAOTest {
    NeedFileDAO needFileDAO;
    Need[] testNeeds;
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
        testNeeds = new Need[3];
        testNeeds[0] = new Need("Bananas", 100, 800, 1000,"Food");
        testNeeds[1] = new Need("Helmet", 50, 700, 1000, "Construction");
        testNeeds[2] = new Need("Nanapkins", 30, 200, 1000, "Sanitary");

        when(mockObjectMapper
                .readValue(new File("doesnt_matter.txt"), Need[].class))
                .thenReturn(testNeeds);
        needFileDAO = new NeedFileDAO("doesnt_matter.txt", mockObjectMapper);
    }

    @Test
    public void testGetNeeds() {
        Need[] Needs = needFileDAO.getNeeds();

        assertEquals(Needs.length, testNeeds.length);
        for (int i = 0; i < testNeeds.length; ++i)
            assertEquals(Needs[i], testNeeds[i]);
    }

    @Test
    public void testFindNeeds() {
        Need[] needs = needFileDAO.findNeeds("na");

        assertEquals(needs.length, 2);
        assertEquals(needs[0], testNeeds[0]);
        assertEquals(needs[1], testNeeds[2]);
    }

    @Test
    public void testGetNeed() {
        Need need = needFileDAO.getNeed("Bananas");

        assertEquals(need, testNeeds[0]);
    }

    @Test
    public void testDeleteNeed() {
        boolean result = assertDoesNotThrow(() -> needFileDAO.deleteNeed("Bananas"),
                "Unexpected exception thrown");

        assertEquals(result, true);
        assertEquals(needFileDAO.needs.size(), testNeeds.length - 1);
    }

    @Test
    public void testCreateNeed() {
        Need need = new Need("Wonder-Person", 100, 1000, 1000, "Toy");

        Need result = assertDoesNotThrow(() -> needFileDAO.createNeed(need),
                "Unexpected exception thrown");

        assertNotNull(result);
        Need actual = needFileDAO.getNeed(need.getName());
        assertEquals(actual.getName(), need.getName());
    }

    @Test
    public void testUpdateNeed() {
        Need need = new Need("Nanapkins", 20, 400, 1000, "Sanitar");

        Need result = assertDoesNotThrow(() -> needFileDAO.updateNeed(need),
                "Unexpected exception thrown");

        assertNotNull(result);
        Need actual = needFileDAO.getNeed(need.getName());
        assertEquals(actual, need);
    }

    @Test
    public void testSaveException() throws IOException {
        doThrow(new IOException())
                .when(mockObjectMapper)
                .writeValue(any(File.class), any(Need[].class));

        Need need = new Need("Wonder-Person", 100, 1000, 1000, "Toy");

        assertThrows(IOException.class,
                () -> needFileDAO.createNeed(need),
                "IOException not thrown");
    }

    @Test
    public void testGetNeedNotFound() {
        Need need = needFileDAO.getNeed("Wipes");

        assertEquals(need, null);
    }

    @Test
    public void testDeleteNeedNotFound() {
        boolean result = assertDoesNotThrow(() -> needFileDAO.deleteNeed("Wipes"),
                "Unexpected exception thrown");

        assertEquals(result, false);
        assertEquals(needFileDAO.needs.size(), testNeeds.length);
    }

    @Test
    public void testUpdateNeedNotFound() {
        Need need = new Need("Wipes", 35, 53, 1000, "Education");

        Need result = assertDoesNotThrow(() -> needFileDAO.updateNeed(need),
                "Unexpected exception thrown");

        assertNull(result);
    }

    @Test
    public void testConstructorException() throws IOException {
        // Setup
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        // We want to simulate with a Mock Object Mapper that an
        // exception was raised during JSON object deseerialization
        // into Java objects
        // When the Mock Object Mapper readValue method is called
        // from the NeedFileDAO load method, an IOException is
        // raised
        doThrow(new IOException())
                .when(mockObjectMapper)
                .readValue(new File("doesnt_matter.txt"), Need[].class);

        // Invoke & Analyze
        assertThrows(IOException.class,
                () -> new NeedFileDAO("doesnt_matter.txt", mockObjectMapper),
                "IOException not thrown");
    }

    // Line 98 and ownwards completed by Abel Girma
}
