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
import com.model.Announcement;
import com.model.Need;
import com.model.Users;


@Tag("Persistence-tier")
public class AnnouncementDAOTest {
    AnnouncementDAO announcementFileDAO;
    Announcement[] testAnnouncements;
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
        testAnnouncements = new Announcement[3];
        testAnnouncements[0] = new Announcement("We Getting 100");
        testAnnouncements[1] = new Announcement("SWEN for life");
        testAnnouncements[2] = new Announcement("CompileGod");

        when(mockObjectMapper
                .readValue(new File("doesnt_matter.txt"), Announcement[].class))
                .thenReturn(testAnnouncements);
        announcementFileDAO = new AnnouncementFileDAO("doesnt_matter.txt", mockObjectMapper);
   }
   
   @Test
   public void testGetAnnouncements() throws IOException {
    Announcement[] announcements = announcementFileDAO.getAnnouncements();
    assertNotNull(announcements);
    assertEquals(3, announcements.length);
    assertEquals("We Getting 100", announcements[0].getDetail());
    assertEquals("SWEN for life", announcements[1].getDetail());
    assertEquals("CompileGod", announcements[2].getDetail());
}
    @Test
    public void testCreateAnnouncement() throws IOException {
        Announcement newAnnouncement = announcementFileDAO.createAnnouncement("New Announcement");
        assertNotNull(newAnnouncement);
        assertEquals("New Announcement", newAnnouncement.getDetail());
    }
    @Test
    public void testDeleteAnnouncement() throws IOException {
        assertTrue(announcementFileDAO.deleteAnnouncement(testAnnouncements[0].getID()));
        assertEquals(2, testAnnouncements.length-1);
    }
}
