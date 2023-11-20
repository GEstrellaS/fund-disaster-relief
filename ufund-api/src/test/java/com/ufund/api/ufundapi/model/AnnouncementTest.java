package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.model.Announcement;

@Tag("Model-tier")
public class AnnouncementTest {

    @Test
    public void testCtor(){
        int expectedID = 0;
        String expectedDetail = "Announcement";
        Announcement expectedAnnouncement = new Announcement(expectedDetail);

        assertEquals(expectedDetail, expectedAnnouncement.getDetail());
        assertEquals(expectedID, expectedAnnouncement.getID());
    }

    @Test
    public void testDetail(){
        String initialDetail = "Announcemen";
        Announcement expectedAnnouncement = new Announcement(initialDetail);

        String expectedDetail = "Announcement";
        expectedAnnouncement.setDetail(expectedDetail);

        assertEquals(expectedDetail, expectedAnnouncement.getDetail());
    }

    @Test
    public void testToString(){
        String detail = "Announcement";
        Announcement announcement = new Announcement(detail);
        String expectedString = String.format(Announcement.STRING_FORMAT, announcement.getID(), detail);

        String actualString = announcement.toString();

        assertEquals(expectedString, actualString);
    }

}
