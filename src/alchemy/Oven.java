package alchemy;

import be.kuleuven.cs.som.annotate.*;

import java.util.ArrayList;

/**
 * A class of ovens.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */
public class Oven extends Device {

    /**********************************************************
     * Constructors
     **********************************************************/
    /**
     * Initialize a new oven with given contents and temperature.
     *
     * @param  	contents
     *         	The contents of the new device.
     * @param  	temperature
     *         	The temperature of the new oven.
     * @effect 	The oven is initialized as a devices
     * 			(contents is set)
     * 			| super(contents)
     * @post	The temperature of this new oven is set to the given temperature.
     * 			| new.getTemperature == temperature
     */
    public Oven(ArrayList<IngredientContainer> contents, int[] temperature) throws IllegalArgumentException {
        super(contents);
        this.temperature = temperature;
    }


    /**********************************************************
     * Contents
     **********************************************************/

    /**
     * Set the contents of this device to the alchemic ingredients in the given containers.
     *
     * @param   containers
     *          The given containers
     * @effect  The contents of this device is expanded with the alchemic ingredients in the given containers.
     *          | for each container in containers
     *          |    contents.add(containers.get(i).getIngredient())
     * @thows   IllegalArgumentException
     *          | containers.size() != 1
     */
    @Override
    public void add(ArrayList<IngredientContainer> containers) throws IllegalArgumentException {
        int length = containers.size();
        if (length != 1){
            throw new IllegalArgumentException("You can only put one thing in the oven at a time. Current number of items : " + length);
        }
        for (int i = 0; i < length; i++){
            this.getContents().add(containers.get(i).getIngredient());
            containers.get(i).terminate();
        }
    }

    /**********************************************************
     * Temperature
     **********************************************************/

    /**
     * Variable referencing the temperature of this cooling box.
     */
    private int[] temperature;

    /**
     * Return the contents of the device.
     */
    @Model
    public int[] getTemperature() {
        return temperature;
    }

    /**
     * Generate a random margin of error for temperature deviation.
     * Selects a random integer value from a predefined range of [-5, 5]
     *
     * @return  A random integer between -5 and 5 (inclusive)
     */
    private int errorOnTemperature(){
        int[] marginOfError = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        int randomIndex = (int)(Math.random() * marginOfError.length);
        return marginOfError[randomIndex];
    }

    /**
     * Applies a random deviation to the current temperature values.
     *
     * @effect  The error is applied to the temperature of the oven
     *          | getTemperature() + error
     */
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

    /**
     * Check whether the temperature of the oven
     * is higher than or the same as the temperature of the contents
     *
     * @return  False if the temperature of the oven is lower than the temperature of the contents
     *          True otherwise.
     *          | if getTemperature() < getContents().get(0).getTemperature()
     *          |   return false
     *          | else return true
     */
    protected boolean canItBeHeated(){
        this.deviateTemperature();
        int coldnessOven = this.getTemperature()[0];
        int hotnessOven = this.getTemperature()[1];
        AlchemicIngredient ingredient = this.getContents().get(0);
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

    /**
     * Heat the contents up if the contents has a lower temperature than the oven.
     *
     * @effect  The full name of the alchemic ingredient is the prefix "Heated" added.
     *          | if canItBeHeated()
     *          | then getContents.get(0).getFullName.equals("Heated"+getContents.get(0).getSimpleName())
     * @effect  The temperature of the alchemic ingredient is set to the temperature of the oven.
     *          | if canItBeHeated()
     *          | then getTemperature().equals(getContents.get(0).getTemperature)
     */
    @Override
    public void use(){
        AlchemicIngredient ingredient = this.getContents().get(0);
        if (this.canItBeHeated()){
            ingredient.addPrefixHeated();
            ingredient.changeTemperature(this.getTemperature());
        }
    }

}
