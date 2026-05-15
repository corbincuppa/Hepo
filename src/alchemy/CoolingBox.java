package alchemy;

public class CoolingBox extends Device{

    /**********************************************************
     * Constructors
     **********************************************************/
    public CoolingBox(IngredientContainer container, int[] temperature) {
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

    private boolean canItBeCooled(){
        int coldnessCoolingBox = this.getTemperature()[0];
        int hotnessCoolingBox = this.getTemperature()[1];
        AlchemicIngredient ingredient = this.getContainer().getIngredient();
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

    protected void cool(){
        AlchemicIngredient ingredient = this.getContainer().getIngredient(); // --> add()????
        //oude container moet vernietigd worden
        if (this.canItBeCooled()){
            ingredient.addPrefixCooled();
            ingredient.changeTemperature(this.getTemperature());
        }
        //nieuwe container moet gemaakt worden
    }



}
