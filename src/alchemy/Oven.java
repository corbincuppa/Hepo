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
    public Oven(IngredientContainer container, int[] temperature) {
        this.container = container;
        this.temperature = temperature;
    }

    /**********************************************************
     * Container
     **********************************************************/

    private IngredientContainer container;

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

    private int errorOnTemperature(){
        int[] marginOfError = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        int randomIndex = (int)(Math.random() * marginOfError.length);
        return marginOfError[randomIndex];
    }

    private void deviateTemperature(){
        int error = errorOnTemperature();
        int coldness = this.getTemperature()[0];
        int hotness = this.getTemperature()[1];
        if (coldness != 0){
            coldness = coldness - error;
        } else{
            hotness = hotness - error;
        }
        if (coldness < 0) {
            hotness = Math.abs(coldness);
            coldness = 0;
        }
        if (hotness < 0) {
            coldness = Math.abs(hotness);
            hotness = 0;
        }
        this.temperature = new int[]{coldness, hotness};
    }

    private boolean canItBeHeated(){
        this.deviateTemperature();
        int coldnessOven = this.getTemperature()[0];
        int hotnessOven = this.getTemperature()[1];
        AlchemicIngredient ingredient = this.getContainer().getIngredient();
        int coldnessIng = ingredient.getColdness();
        int hotnessIng = ingredient.getHotness();
        if (coldnessOven == 0){
            if (hotnessIng <= hotnessOven){
                return true;
            }
            return false;
        }
        if (coldnessIng >= coldnessOven){
            return true;
        }
        return false;
    }

    /**********************************************************
     * Methods
     **********************************************************/

    protected void heat(){
        AlchemicIngredient ingredient = this.getContainer().getIngredient();
        //oude container moet vernietigd worden
        if (this.canItBeHeated()){
            ingredient.addPrefixHeated();
            ingredient.changeTemperature(this.getTemperature());
        }
        //nieuwe container moet gemaakt worden
    }

}
