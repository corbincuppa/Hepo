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
    protected ArrayList<AlchemicIngredient> contents = new ArrayList<AlchemicIngredient>();

    public ArrayList<AlchemicIngredient> getContents() {
        return contents;
    }


    public void add(ArrayList<IngredientContainer> containers) {
        int length = containers.size();
        for (int i = 0; i < length; i++) {
            contents.add(containers.get(i).getIngredient());
            containers.get(i).terminate();
        }
    }

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
