import be.kuleuven.cs.som.annotate.*;

/**
 * A class of ingredient types.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public enum IngredientType {

    /**********************************************************
     * IngredientType
     **********************************************************/
    WATER("water", State.LIQUID, new int[]{0, 20});

    /**********************************************************
     * Name
     **********************************************************/
    private final String name;

    // check valid name (eenvoudige naam.upper())

    /**********************************************************
     * Standard state
     **********************************************************/
    protected final State stdState;

    /**********************************************************
     * Standard temperature
     **********************************************************/
    protected final int[] stdTemp;
    // Temperature class or ArrayList?

    /**********************************************************
     * Constructors
     **********************************************************/
    IngredientType(String name, State stdState, int[] stdTemp) {
        this.name = name;
        this.stdState = stdState;
        this.stdTemp = stdTemp;
    }

}
