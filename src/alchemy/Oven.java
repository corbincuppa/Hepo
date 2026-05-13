package alchemy;

public class Oven extends Device {

    /**********************************************************
     * Constructors
     **********************************************************/



    /**********************************************************
     * Contents
     **********************************************************/



    /**********************************************************
     * Temperature
     **********************************************************/



    /**********************************************************
     * Methods
     **********************************************************/

    //only oven can use this

    /**
     * Add "Heated" to the full name of the given alchemic ingredient.
     *
     * @param ingredient
     *        The given alchemic ingredient
     */
    protected void addPrefixHeated(AlchemicIngredient ingredient){
        String newName = "Heated" + ingredient.getFullName();
        ingredient.changeFullName(newName);
    }


}
