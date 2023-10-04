package com.classes;

/*
 * Class for Need
*/
public class Need {
    private String name;
    private double cost;
    private int quantity;
    private String type;

    public Need(String name, double cost, int quantity, String type){
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

}
