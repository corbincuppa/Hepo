package alchemy;

public class CoolingBox extends Device{

    /**********************************************************
     * Constructors
     **********************************************************/
    public CoolingBox(int temperature) {
        setTemperature(temperature);
    }


    /**********************************************************
     * Contents
     **********************************************************/
    // super(contents)


    /**********************************************************
     * Temperature
     **********************************************************/


    public final int temperature = 0;

    protected void setTemperature(int newTemp) {
        this.temperature += temperature;
    }



    /**********************************************************
     * Methods
     **********************************************************/

    /**
     * Add "Cooled" to the full name of the given alchemic ingredient.
     *
     * @param ingredient
     *        The given alchemic ingredient
     */
    protected void addPrefixCooled(AlchemicIngredient ingredient){
        String newName = "Cooled" + ingredient.getFullName();
        ingredient.setFullName(newName);
    }



}
