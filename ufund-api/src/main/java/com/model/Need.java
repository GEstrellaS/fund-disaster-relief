package com.model;

import java.util.logging.Logger;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Class for Need
*/
public class Need {
    private static final Logger LOG = Logger.getLogger(Need.class.getName());
    static final String STRING_FORMAT = "Need [name=%s, cost=%.2f, quantity=%d, type=%s]";
    @JsonProperty("name") private String name;
    @JsonProperty("cost") private double cost;
    @JsonProperty("quantity") private int quantity;
    @JsonProperty("type") private String type;

    public Need(@JsonProperty("name") String name, @JsonProperty("cost") double cost, @JsonProperty("quantity") int quantity, @JsonProperty("type") String type){
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.type = type;
    }

    /*
     * gets the name of the Need
     * @return name name of the need
     */
    public String getName(){
        return this.name;
    }

    /*
     * sets the name 
     * @param name the new Name
     */
    public void setName(String name){
        this.name = name;
    }

    /*
     * gets the cost of the Need
     * @return cost cost of the need
     */
    public double getCost(){
        return this.cost;
    }

     /*
     * sets the cost 
     * @param cost the new cost
     */
    public void setCost(double cost){
        this.cost = cost;
    }

    /*
     * gets the type of the Need
     * @return type type of the need
     */
    public String getType(){
        return this.type;
    }

    /*
     * sets the type 
     * @param type the new type
     */
    public void setType(String type){
        this.type = type;
    }

    /*
     * gets the quantity of the Need
     * @return quantity quantity of the need
     */
    public int getQuantity(){
        return this.quantity;
    }

    /*
     * sets the quantity 
     * @param quantity the new quantity
     */
    public void setQuantity(int amount){
        this.quantity = amount;
    }

    @Override
    public String toString(){
        return String.format(STRING_FORMAT, name, cost, quantity, type);
    }
}
