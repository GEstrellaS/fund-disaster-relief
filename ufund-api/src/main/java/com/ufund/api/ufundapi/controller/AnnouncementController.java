package com.ufund.api.ufundapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Announcement;
import com.ufund.api.ufundapi.persistence.AnnouncementDAO;

@RestController
@RequestMapping("announcements")
public class AnnouncementController {
    private static final Logger LOG = Logger.getLogger(AnnouncementController.class.getName());
    private AnnouncementDAO announcementDAO;

    public AnnouncementController(AnnouncementDAO announcementDAO){
        this.announcementDAO = announcementDAO;
    }

    @GetMapping("")
    public ResponseEntity<Announcement[]> getAnnouncement() {
        LOG.info("GET /announcement");

        try {
            Announcement[] announcement = announcementDAO.getAnnouncements();
            if (announcement!= null){
                return new ResponseEntity<>(announcement,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement) {
        LOG.info("POST /announcements " + announcement);
        try {
            Announcement newAnnouncement = announcementDAO.createAnnouncement(announcement);
            if (newAnnouncement != null) {
                return new ResponseEntity<>(newAnnouncement, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e){
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Announcement> deleteNeed(@PathVariable int id) {
        LOG.info("DELETE /announcements/" + id);
        try {
            boolean delete = announcementDAO.deleteAnnouncement(id);
            if(delete){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
            
    }
}
