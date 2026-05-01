package alchemy;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of alchemic ingredients.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public class AlchemicIngredient extends IngredientType{

    /**********************************************************
     * Constructors
     **********************************************************/

    /**
     * Create a new alchemic ingredient with a given name, ingredient type,
     * state, quantity and temperature.
     *
     * @param name
     *        The given name for the alchemic ingredient.
     * @param ingredientType
     *        The given ingredient type of the alchemic ingredient.
     * @param state
     *        The given state of the alchemic ingredient.
     * @param quantity
     *        The given quantity of the alchemic ingredient.
     * @param temperature
     *        The given temperature of the alchemic ingredient.
     */
    public void AlchemicIngredient(String name, IngredientType ingredientType, State state, int quantity, int[] temperature) {
        setName(name);
        setIngredientType(ingredientType);
        setState(state);
        setQuantity(quantity);
        setTemperature(temperature);
    }



    /**********************************************************
     * Name - Defensive programming
     **********************************************************/



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
    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Return the quantity of this alchemic ingredient.
     */
    protected int getQuantity() {
        return this.quantity;
    }

    // CHECK VALID QUANTITY


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



}
