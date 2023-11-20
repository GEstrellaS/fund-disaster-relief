package com.model;

import java.util.logging.Logger;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 
 * Defines the class for Announcement
 */
public class Announcement{
    private static final Logger LOG = Logger.getLogger(Announcement.class.getName());
    public final static String STRING_FORMAT = "Announcement [id=%d, detail=%s]";
    private static int classID = 0;
    @JsonProperty("id") private int id;
    @JsonProperty("detail") private String detail;

    /**
     * Announcement constructor
     * @param detail
     */
    public Announcement(@JsonProperty("detail") String detail){
        this.detail = detail;
        this.id = classID;
        classID++;
    }

    /**
     * gets the id of the Announcement
     * @return int
     */
    public int getID(){
        return this.id;
    }

    /**
     * gets the detail of the announcement
     * @return String
     */
    public String getDetail(){
        return this.detail;
    }
    
    /**
     * sets the detail
     * @param detail the new detail
     */
    public void setDetail(String detail){
        this.detail = detail;
    }

    /** 
     * overrides the default toString and implements a new string format for the Need
     */
    @Override
    public String toString(){
        return String.format(STRING_FORMAT, this.id, this.detail);
    }

}