package alchemy;

/**
 * A class of ingredient types.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public enum IngredientType {

    /**********************************************************
     * alchemy.IngredientType
     **********************************************************/
    WATER("water", State.LIQUID, new int[]{0, 20});

    /**********************************************************
     * Name
     **********************************************************/
    // FINAL??
    private String name;

    // check valid name (eenvoudige naam.upper())

    /**********************************************************
     * Standard state
     **********************************************************/
    // FINAL??
    protected State stdState;

    /**********************************************************
     * Standard temperature
     **********************************************************/
    // FINAL??
    protected int[] stdTemp;
    // Temperature class or ArrayList?

    /**********************************************************
     * Constructors
     **********************************************************/
    /**
     *
     * @param name
     * @param stdState
     * @param stdTemp
     */
    protected IngredientType(String name, State stdState, int[] stdTemp) {
        if (isValidName(name) == true) {
            this.name = name;}
        this.stdState = stdState;
        this.stdTemp = stdTemp;
    }

}