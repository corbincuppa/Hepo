package alchemy;

import java.util.ArrayList;

public class CoolingBox extends Device{

    /**********************************************************
     * Constructors
     **********************************************************/
    public CoolingBox(ArrayList<IngredientContainer> contents, int[] temperature) {
        super(contents);
        this.temperature = temperature;
    }


    /**********************************************************
     * Container
     **********************************************************/

    public AlchemicIngredient getAlchemicIngredient() {
        int length = this.getAlchemicIngredients().size();
        if (length != 1){
            throw new IllegalArgumentException("You can only put one thing in the cooling box at a time. Current number of items : " + length);
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

    private boolean canItBeCooled(){
        int coldnessCoolingBox = this.getTemperature()[0];
        int hotnessCoolingBox = this.getTemperature()[1];
        AlchemicIngredient ingredient = this.getAlchemicIngredient();
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

    @Override
    public void use(){
        AlchemicIngredient ingredient = this.getAlchemicIngredient();
        IngredientContainer oldContainer = this.getContents().get(0);
        UnitOfQuantity capacity = oldContainer.getCapacity();
        if (this.canItBeCooled()){
            ingredient.addPrefixCooled();
            ingredient.changeTemperature(this.getTemperature());
        }
        new IngredientContainer(capacity, ingredient);
    }



}
