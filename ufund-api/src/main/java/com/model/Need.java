package com.model;

import java.util.logging.Logger;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines the class for Need
 */
public class Need {
    private static final Logger LOG = Logger.getLogger(Need.class.getName());
    public final static String STRING_FORMAT = "Need [name=%s, cost=%.2f, quantity=%d, type=%s]";
    @JsonProperty("name") private String name;
    @JsonProperty("cost") private double cost;
    @JsonProperty("currentQuantity") private int currentQuantity;
    @JsonProperty("requiredQuantity") private int requiredQuantity;
    @JsonProperty("type") private String type;

    /**
     * Need constructor
     */
    public Need(@JsonProperty("name") String name, @JsonProperty("cost") double cost, @JsonProperty("currentQuantity") int currentQuantity, @JsonProperty("requiredQuantity") int requiredQuantity, @JsonProperty("type") String type){
        this.name = name;
        this.cost = cost;
        this.currentQuantity = currentQuantity; 
        this.requiredQuantity = requiredQuantity; 
        this.type = type;
    }

    /**
     * gets the name of the Need
     * @return name name of the need
     */
    public String getName(){
        return this.name;
    }

    /**
     * sets the name 
     * @param name the new Name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * gets the cost of the Need
     * @return cost cost of the need
     */
    public double getCost(){
        return this.cost;
    }

     /**
     * sets the cost 
     * @param cost the new cost
     */
    public void setCost(double cost){
        this.cost = cost;
    }

    /**
     * gets the type of the Need
     * @return type type of the need
     */
    public String getType(){
        return this.type;
    }

    /**
     * sets the type 
     * @param type the new type
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * gets the quantity of the Need
     * @return quantity quantity of the need
     */
    public int getCurrentQuantity(){
        return this.currentQuantity;
    }

    public int getRequiredQuantity(){
        return this.requiredQuantity;
    }

    /**
     * sets the quantity 
     * @param quantity the new quantity
     */
    public void setCurrentQuantity(int amount){
        this.currentQuantity = amount;
    }

    public void setRequiredQuantity(int amount){
        this.requiredQuantity = amount;
    }

    /**
     * overrides the default toString and implements a new string format for Need
     */
    @Override
    public String toString(){
        return String.format(STRING_FORMAT, name, cost, currentQuantity, requiredQuantity,type);
    }
}
