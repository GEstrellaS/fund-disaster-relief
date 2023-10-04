package com.classes;

import java.util.ArrayList;

public class Cupboard {
    private ArrayList<Need> needs;

    public Cupboard(ArrayList<Need> needs){
        this.needs = needs;
    }

    public int searchNeed(Need need){
        return 0;
    }

    public void addNeed(Need need){
        this.needs.add(need);
    }

    public void deleteNeed(){

    }

    public boolean isNeedAvailable(){
        return false;
    }


}
