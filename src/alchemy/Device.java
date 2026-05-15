package alchemy;

import java.util.ArrayList;

/**
 * A class of devices.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public abstract class Device {

    /**********************************************************
     * Constructors
     **********************************************************/

    public Device(IngredientContainer container) {
        add(container);
    }
    public Device() {}



    /**********************************************************
     * Contents
     **********************************************************/

    /**
     * Variable referencing the contents of this device.
     */
    private ArrayList<AlchemicIngredient> contents ;

    public ArrayList<AlchemicIngredient> getContents() {
        return contents;
    }

    /**
     * Add the ingredient inside a given container to this device.
     *
     * @param   container
     *          The given container containing the alchemic ingredient to be added
     * @effect  The given container is terminated.
     *          | container.terminate()
     */
    public void add(IngredientContainer container){;
        contents.add(container.getIngredient());
        // Delete old container
        container.terminate();
    }

    /**
     * Take the results from this device.
     *
     * @return  The result, stored in the contents of this device, if there is only one ingredient in this device.
     *          | if (contents.size() == 2) then result == contents.get(0)
     */
    public AlchemicIngredient takeResult() {
        if (contents.size() == 1) {
            return contents.get(0);
        }
    }

    /**
     * Use this device.
     */
    public void use() {
        // INSERT use method here for each device
    }
}
