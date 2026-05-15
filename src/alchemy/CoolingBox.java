package alchemy;

import java.util.ArrayList;

public class CoolingBox extends Device{

    /**********************************************************
     * Constructors
     **********************************************************/
    public CoolingBox(ArrayList<IngredientContainer> contents, int[] temperature) throws IllegalArgumentException{
        super(contents);
        this.temperature = temperature;
    }


    /**********************************************************
     * Container
     **********************************************************/

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

    private int[] temperature;

    public int[] getTemperature() {
        return temperature;
    }

    protected boolean canItBeCooled(){
        int coldnessCoolingBox = this.getTemperature()[0];
        int hotnessCoolingBox = this.getTemperature()[1];
        AlchemicIngredient ingredient = this.getContents().getFirst();
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
        AlchemicIngredient ingredient = this.getContents().get(0);
        if (this.canItBeCooled()){
            ingredient.addPrefixCooled();
            ingredient.changeTemperature(this.getTemperature());
        }
    }
}
