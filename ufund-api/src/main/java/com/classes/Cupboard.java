package com.classes;

import java.util.ArrayList;
import com.model.Need;

/**
 * Defines the class for the cupboard
 */
public class Cupboard {
    private ArrayList<Need> needs;

    /**
     * Cupboard constructor
     * @param needs
     */
    public Cupboard(ArrayList<Need> needs){
        this.needs = needs;
    }

    /**
     * Searches the amount of needs in the cupboard
     * @param need
     * @return number of needs in the cupboard
     */
    public int searchNeed(Need need){
        return 0;
    }

    /**
     * adds the need to the cupboard
     * @param need
     */
    public void addNeed(Need need){
        this.needs.add(need);
    }

    /**
     * deletes a Need from the cupboard
     */
    public void deleteNeed(){

    }

    /**
     * checks for availability of a need
     * @return true if it is available, false if it is not
     */
    public boolean isNeedAvailable(){
        return false;
    }
}
