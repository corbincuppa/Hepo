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

    public ArrayList<AlchemicIngredient> getAlchemicIngredients() {
        int length = this.getContents().size();
        ArrayList<AlchemicIngredient> ingredients= new ArrayList<AlchemicIngredient>();
        for (int i = 0; i < length; i++){
            ingredients.add(this.getContents().get(i).getIngredient());
        }
        return ingredients;
    }


    /**
     * Add the ingredient inside a given container to this device.
     *
     * @param   container
     *          The given container containing the alchemic ingredient to be added
     */
    public void add(IngredientContainer container){
        contents.add(container.getIngredient());
        container.terminate();
    }
    //--> zouden we het wegdoen, we gebruiken het niet

    public void takeResult() {
    }
    //--> zouden we het wegdoen, we gebruiken het niet

    public void use() {
        // INSERT use method here for each device
    }


}
