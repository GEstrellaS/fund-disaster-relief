package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.ufund.api.ufundapi.persistence.AnnouncementDAO;
import com.model.Announcement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag("Controller-tier")
public class AnnouncementControllerTest {
    private AnnouncementController announcementController;
    private AnnouncementDAO mockAnnouncementDAO;


    @BeforeEach
    public void setupAnnouncementController() throws IOException{
        mockAnnouncementDAO = mock(AnnouncementDAO.class);
        announcementController = new AnnouncementController(mockAnnouncementDAO);
    }

    @Test
    public void testCreateAnnouncement() throws IOException{
        Announcement announcement = new Announcement("Announcement");

        when(mockAnnouncementDAO.createAnnouncement("Announcement")).thenReturn(announcement);

        ResponseEntity<String> response = announcementController.createAnnouncement("Announcement");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(announcement.toString(), response.getBody());
    }

    @Test
    public void testCreateAnnouncementException() throws IOException{
        String announcement = "Announcement";
        doThrow(new IOException()).when(mockAnnouncementDAO).createAnnouncement(announcement);

        ResponseEntity<String> response = announcementController.createAnnouncement(announcement);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
    
    @Test
    public void testGetAnnouncements() throws IOException{
        Announcement[] announcements = new Announcement[2];
        announcements[0] = new Announcement("1");
        announcements[1] = new Announcement("2");

        when(mockAnnouncementDAO.getAnnouncements()).thenReturn(announcements);

        ResponseEntity<Announcement[]> response = announcementController.getAnnouncements();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(announcements, response.getBody());
    }

    @Test
    public void testGetAnnouncementsException() throws IOException{
        doThrow(new IOException()).when(mockAnnouncementDAO).getAnnouncements();

        ResponseEntity<Announcement[]> response = announcementController.getAnnouncements();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(null, response.getBody());
    }



    @Test
    public void testDeleteAnnouncement() throws IOException{
        int id = 1;

        when(mockAnnouncementDAO.deleteAnnouncement(id)).thenReturn(true);

        ResponseEntity<Announcement> response = announcementController.deleteNeed(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteAnnouncementFail() throws IOException{
        int id = 1;

        when(mockAnnouncementDAO.deleteAnnouncement(id)).thenReturn(false);

        ResponseEntity<Announcement> response = announcementController.deleteNeed(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteAnnouncementException() throws IOException{
        int id = 1;

        doThrow(new IOException()).when(mockAnnouncementDAO).deleteAnnouncement(id);

        ResponseEntity<Announcement> response = announcementController.deleteNeed(id);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
