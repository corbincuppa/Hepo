package alchemy;

import java.util.ArrayList;

/**
 * A class of devices.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public class Device {

    /**********************************************************
     * Constructors
     **********************************************************/

    public Device() {

    }



    /**********************************************************
     * Contents
     **********************************************************/
    private ArrayList<AlchemicIngredient> contents = new ArrayList<AlchemicIngredient>();



    /**********************************************************
     *
     **********************************************************/
    public void add(IngredientContainer container){
        // deletes old container
        if (contents.size() == 0) {
            contents.add(container.getIngredient());
        }
    }

    public void takeResult() {
        // makes new container
    }

    public void use() {
        // INSERT use method here for each device
    }


}
