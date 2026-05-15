package alchemy;

import be.kuleuven.cs.som.annotate.*;
import exceptions.*;

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
    /**
     * Create a new device with a given contents
     *
     * @param   contents
     *          The given contents of the device
     * @effect	The contents of this device is expanded with the alchemic ingredients in the given containers.
     *          | setWritable(writable)
     */
    public Device(ArrayList<IngredientContainer> contents) {
        add(contents);
    }



    /**********************************************************
     * Contents
     **********************************************************/

    /**
     * Variable referencing the contents of this device.
     */
    protected ArrayList<AlchemicIngredient> contents = new ArrayList<AlchemicIngredient>();

    /**
     * Return the contents of the device.
     */
    @Model
    public ArrayList<AlchemicIngredient> getContents() {
        return contents;
    }

    /**
     * Set the contents of this device to the alchemic ingredients in the given containers.
     *
     * @param   containers
     *          The given containers
     * @effect  The contents of this device is expanded with the alchemic ingredients in the given containers.
     *          | for each container in containers
     *          |    contents.add(containers.get(i).getIngredient())
     */
    public void add(ArrayList<IngredientContainer> containers) {
        int length = containers.size();
        for (int i = 0; i < length; i++) {
            contents.add(containers.get(i).getIngredient());
            containers.get(i).terminate();
        }
    }

    /**
     * Make a container for the alchemic ingredient in the devices.
     *
     * @effect  A container is made if there is only 1 alchemic ingredient in the device.
     *          | if contents.size() == 1
     *          |   new IngredientContainer(getContents().get(0);)
     * @throws  IllegalAmountException
     *          | contents.size() != 1
     */
    public IngredientContainer takeResult() throws IllegalAmountException {
        if(contents.size() == 1) {
            AlchemicIngredient ing = this.getContents().get(0);
            return new IngredientContainer(ing);
        }
        throw new IllegalAmountException(contents.size());
    }

    /**
     * Use this device.
     */
    public void use() {
        // INSERT use method here for each device
    }
}
