package alchemy;

import be.kuleuven.cs.som.annotate.*;

import java.util.ArrayList;

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
    public Oven(ArrayList<IngredientContainer> contents, int[] temperature) {
        super(contents);
        this.temperature = temperature;
    }

    /**********************************************************
     * Container
     **********************************************************/


    public AlchemicIngredient getAlchemicIngredient() {
        int length = this.getAlchemicIngredients().size();
        if (length != 1){
            throw new IllegalArgumentException("You can only put one thing in the oven at a time. Current number of items : " + length);
        }
        return this.getAlchemicIngredients().get(0);
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
        AlchemicIngredient ingredient = this.getAlchemicIngredient();
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

    @Override
    public void use(){
        AlchemicIngredient ingredient = this.getAlchemicIngredient();
        IngredientContainer oldContainer = this.getContents().get(0);
        UnitOfQuantity capacity = oldContainer.getCapacity();
        oldContainer.terminate();
        if (this.canItBeHeated()){
            ingredient.addPrefixHeated();
            ingredient.changeTemperature(this.getTemperature());
        }
        new IngredientContainer(capacity, ingredient);
    }

}
