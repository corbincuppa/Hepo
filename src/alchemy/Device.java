package alchemy;

import exceptions.IllegalAmountException;

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

    public Device(ArrayList<IngredientContainer> contents) {
        add(contents);
    }



    /**********************************************************
     * Contents
     **********************************************************/

    /**
     * Variable referencing the contents of this device.
     */
    private ArrayList<AlchemicIngredient> contents = new ArrayList<AlchemicIngredient>();

    public ArrayList<AlchemicIngredient> getContents() {
        return contents;
    }

    public void add(ArrayList<IngredientContainer> containers) {
        int length = containers.size();
        for (int i = 0; i < length; i++){
            contents.add(containers.get(i).getIngredient());
            containers.get(i).terminate();
        }
    }

    /**
     * Take the results from this device.
     *
     * @return  The result, stored in the contents of this device, if there is only one ingredient in this device.
     *          | if (contents.size() == 2) then result == contents.get(0)
     * @throws  IllegalAmountException
     *          | contents.size() > 1
     */
    public AlchemicIngredient takeResult() throws IllegalAmountException {
        if (contents.size() == 1) {
            return contents.get(0);
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
