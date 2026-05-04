package alchemy;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of alchemic ingredients.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public class AlchemicIngredient {

    /**********************************************************
     * Constructors
     **********************************************************/

    /**
     * Create a new alchemic ingredient with a given name, ingredient type,
     * state, quantity and temperature.
     *
     * @param ingredientType
     *        The given ingredient type of the alchemic ingredient.
     * @param quantity
     *        The given quantity of the alchemic ingredient.
     */
    public void AlchemicIngredient( IngredientType ingredientType, int quantity) {
        String simpleName = ingredientType.getName(); // is het nodig? --> getSimpleName = this.getIngredientType.getName()
        String fullName = ingredientType.getName();
        String specialName = null;
        setIngredientType(ingredientType);
        State state = ingredientType.getStdState();
        setQuantity(quantity);
        int[] temperature = ingredientType.getStdTemp();
    }



    /**********************************************************
     * Name - Defensive programming
     **********************************************************/
    // adding prefix (heated / cooled) to full name
    // the possibility to add a special name
    // get simple, full(if full==simple, return null??) and special name
    // for things that has been in the kettle "mixed with" needs to be added


    /**********************************************************
     * IngredientType
     **********************************************************/
    //      THIS OR THE ALCHEMIC INGREDIENT? PERIOD OR NO PERIOD FOR JAVADOC COMMENTS?????????????
    /**
     * The ingredient type of the alchemic ingredient.
     */
    private IngredientType ingredientType;

    /**
     * Set the ingredient type of this alchemic ingredient to the
     * given ingredient type.
     *
     * @param ingredientType
     *        The given ingredient type.
     */
    private void setIngredientType(IngredientType ingredientType){
        this.ingredientType = ingredientType;
    }

    public IngredientType getIngredientType() {
        return ingredientType;
    }

    /**********************************************************
     * State
     **********************************************************/
    /**
     * The state of the alchemic ingredient.
     */
    public State state;

    /**
     * Change the state of this alchemic ingredient to the
     * given state.
     *
     * @param state
     *        The given state
     */
    private void setState(State state) {
        this.state = state;
    }
    // --> if it has been in the Transmogrifier



    /**********************************************************
     * Quantity - Nominal programming
     **********************************************************/
    /**
     * The quantity of the alchemic ingredient expressed in SPOONS:
     *      - drop, spoon, vial, bottle, jug, barrel, storeroom for this.state == LIQUID
     *      - pinch, spoon, sachet, box, sack, chest, storeroom for this.state == POWDER
     */
    private int quantity;

    /**
     * Change the quantity of this alchemic ingredient to the
     * given quantity.
     *
     * @param quantity
     *        The given quantity
     */
    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Return the quantity of this alchemic ingredient.
     */
    protected int getQuantity() {
        return this.quantity;
    }

    // CHECK VALID QUANTITY --> hoeft niet, nominaal programmeren


    /**********************************************************
     * Temperature
     **********************************************************/
    /**
     * The current temperature of the alchemic ingredient.
     */
    private int[] temperature;

    /**
     * Change the curren temperature of this alchemic ingredient to the
     * given temperature.
     *
     * @param temperature
     *        The given temperature.
     */
    private void setTemperature(int[] temperature) {
        this.temperature = temperature;
    }

    // if it has been in the oven or cooler



}
