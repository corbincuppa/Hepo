package alchemy;

import be.kuleuven.cs.som.annotate.*;
import java.util.ArrayList;

public class CoolingBox extends Device{

    /**********************************************************
     * Constructors
     **********************************************************/
    /**
     * Initialize a new cooling box with given contents and temperature.
     *
     * @param  	contents
     *         	The contents of the new device.
     * @param  	temperature
     *         	The temperature of the new cooling box.
     * @effect 	The cooling box is initialized as a devices
     * 			(contents is set)
     * 			| super(contents)
     * @post	The temperature of this new cooling box is set to the given temperature.
     * 			| new.getTemperature == temperature
     */
    public CoolingBox(ArrayList<IngredientContainer> contents, int[] temperature) throws IllegalArgumentException{
        super(contents);
        this.temperature = temperature;
    }


    /**********************************************************
     * Container
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
            throw new IllegalArgumentException("You can only put one thing in the cooling box at a time. Current number of items : " + length);
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
     * Check whether the temperature of the cooling box
     * is lower than or the same as the temperature of the contents
     *
     * @return  False if the temperature of the cooling box is higher than the temperature of the contents
     *          True otherwise.
     *          | if getTemperature() > getContents().get(0).getTemperature()
     *          |   return false
     *          | else return true
     */
    protected boolean canItBeCooled(){
        int coldnessCoolingBox = this.getTemperature()[0];
        int hotnessCoolingBox = this.getTemperature()[1];
        AlchemicIngredient ingredient = this.getContents().get(0);
        int coldnessIng = ingredient.getColdness();
        int hotnessIng = ingredient.getHotness();
        if (coldnessCoolingBox == 0){
            if (hotnessIng >= hotnessCoolingBox){
                return true;
            }
            return false;
        }
        if (coldnessIng <= coldnessCoolingBox){
            return true;
        }
        return false;
    }


    /**********************************************************
     * Methods
     **********************************************************/

    /**
     * Cool the contents down if the contents has a higher temperature than the cooling box.
     *
     * @effect  The full name of the alchemic ingredient is the prefix "Cooled" added.
     *          | if canItBeCooled()
     *          | then getContents.get(0).getFullName.equals("Cooled"+getContents.get(0).getSimpleName())
     * @effect  The temperature of the alchemic ingredient is set to the temperature of the cooling box.
     *          | if canItBeCooled()
     *          | then getTemperature().equals(getContents.get(0).getTemperature)
     */
    @Override
    public void use(){
        AlchemicIngredient ingredient = this.getContents().get(0);
        if (this.canItBeCooled()){
            ingredient.addPrefixCooled();
            ingredient.changeTemperature(this.getTemperature());
        }
    }
}
