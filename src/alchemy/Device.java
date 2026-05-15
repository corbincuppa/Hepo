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

    /**
     * Variable referencing the contents of this device.
     */
    private ArrayList<AlchemicIngredient> contents = new ArrayList<AlchemicIngredient>();



    /**********************************************************
     * Use
     **********************************************************/

    /**
     * Add the ingredient inside a given container to this device.
     *
     * @param   container
     *          The given container containing the alchemic ingredient to be added
     */
    public void add(IngredientContainer container){
        // deletes old container
        if (contents.size() == 0) {
            contents.add(container.getIngredient());
        }
        container.terminate();
    }

    public void takeResult() {
        // makes new container
    }

    public void use() {
        // INSERT use method here for each device
    }


}
