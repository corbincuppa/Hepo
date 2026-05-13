package alchemy;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of oven
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public class Oven extends Device {

    /**********************************************************
     * Constructors
     **********************************************************/
    public void Oven(IngredientContainer container, int[] temperature) {
        this.container = container;
        this.temperature = temperature;
    }

    /**********************************************************
     * Container
     **********************************************************/

    private IngredientContainer container; //

    public IngredientContainer getContainer() {
        return container;
    }

    /**********************************************************
     * Temperature
     **********************************************************/

    private int[] temperature;

    public int[] getTemperature() {
        return temperature;
    }

    private boolean canItBeHeated(){
        int coldness = this.getTemperature()[0];
        int hotness = this.getTemperature()[1];
        AlchemicIngredient ingredient = this.getContainer().getIngredient();
        int coldness = ingredient.getColdness();
        int hotness = ingredient.getHotness();
        // als temp van ingredien lager dan van temp van over --> true
    }

    /**********************************************************
     * Methods
     **********************************************************/

    protected void heat(){
        AlchemicIngredient ingredient = this.getAlchemicIngredient();
        if canItBeHeated(){
            ingredient.addPrefixHeated();
            ingredient.changeTemperature();

        }
    }

}
