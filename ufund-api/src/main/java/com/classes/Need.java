package com.classes;

public class Need {
    private String need;
    private int amount;

    public Need(String need, int amount){
        this.need = need;
        this.amount = amount;
    }

    public String getNeed(){
        return this.need;
    }

    public int getAmount(){
        return this.amount;
    }
}
