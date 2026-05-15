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

    public Device(ArrayList<IngredientContainer> contents) {
        setContents(contents);
    }



    /**********************************************************
     * Contents
     **********************************************************/

    /**
     * Variable referencing the contents of this device.
     */
    private ArrayList<IngredientContainer> contents ;

    public ArrayList<IngredientContainer> getContents() {
        return contents;
    }

    public void setContents(ArrayList<IngredientContainer> contents) {
        this.contents = contents;
    }
    // bij oven enzo exception gooien als lengte != 1?


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
    }

    public void takeResult() {
        // makes new container
    }

    public void use() {
        // INSERT use method here for each device
    }


}
