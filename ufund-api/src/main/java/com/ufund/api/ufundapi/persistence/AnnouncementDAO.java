package com.ufund.api.ufundapi.persistence;

import java.io.IOException;

import com.model.Announcement;

/**
 * Defines the interfacte for the Announcement object persistence
 */
public interface AnnouncementDAO {

    /**
     * Retrieves all {@linkplain Announcement announcements}
     * @return An array of {@link Announcement announcements} objecrs, may be empty
     * @throws IOException if an issue with underlying storage
     */
    Announcement[] getAnnouncements() throws IOException;

    /**
     * Creates and saves a {@linkplain Announcement announcement}
     * @param announcement {@linkplain Announcement announcement} object to be created and saves
     * @return new {@link Announcement announcement} if succesful, false otherwise
     * @throws IOException if an issue with underlying storage
     */
    Announcement createAnnouncement(Announcement announcement) throws IOException;

    /**
     * Deletes a {@linkplain Announcement announcement} with the given id
     * @param id the id of the {@link Announcement announcement}
     * @return true if the {@link Announcement announcement} was deleted
     * <br>
     * false if announcement with the given id does not exist
     * @throws IOException
     */
    boolean deleteNeed(int id) throws IOException;
}
