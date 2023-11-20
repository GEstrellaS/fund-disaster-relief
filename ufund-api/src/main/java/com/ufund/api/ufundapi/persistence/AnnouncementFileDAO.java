package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Announcement;
import com.model.Need;

/**
 * Implements the functionality for JSON file-based persistance for Announcements
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 */
@Component
public class AnnouncementFileDAO implements AnnouncementDAO{
    private static final Logger LOG = Logger.getLogger(AnnouncementFileDAO.class.getName());
    Map<Integer, Announcement> announcements;
    private ObjectMapper objectMapper;
    private String filename;

    /**
     * Creates a Announcement File Data Access Object
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * @throws IOException when file cannot be accessed or read from
     */
    public AnnouncementFileDAO(@Value("${announcements.file}") String filename, ObjectMapper objectMapper) throws IOException{
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    /**
     * Loads {@linkplain Announcment announcement} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException{
        announcements = new TreeMap<>();
        Announcement[] announcementArray = objectMapper.readValue(new File(filename), Announcement[].class);
        for(Announcement announcement: announcementArray){
            announcements.put(announcement.getID(), announcement);
        }
        return true;
    }

    /**
     * Saves the {@linkplain Announcement announcements} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link Announcement announcements} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException{
        Announcement[] announcementArray = getAnnouncementsArray();
        objectMapper.writeValue(new File(filename), announcementArray);
        return true;
    }

    /**
     * Generates an array of {@linkplain Announcement announcements} from the tree map for any
     * @return  The array of {@link Announcement needs}, may be empty
     */
    private Announcement[] getAnnouncementsArray(){
        ArrayList<Announcement> announcementArrayList = new ArrayList<>();
        for(Announcement announcement: announcements.values()){
            announcementArrayList.add(announcement);
        }

        Announcement[] announcementArray = new Announcement[announcementArrayList.size()];
        announcementArrayList.toArray(announcementArray);
        return announcementArray;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Announcement[] getAnnouncements() throws IOException {
        synchronized(announcements){
        return getAnnouncementsArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Announcement createAnnouncement(Announcement announcement) throws IOException {
        synchronized(announcements){
            Announcement newAnnouncement = new Announcement(announcement.getDetail());
            announcements.put(announcement.getID(), newAnnouncement);
            save();
            return newAnnouncement;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteAnnouncement(int id) throws IOException {
        synchronized(announcements){
            if(announcements.containsKey(id)){
                announcements.remove(id);
                return save();
            }else{
                return false;
            }
        }
    }
    
}
