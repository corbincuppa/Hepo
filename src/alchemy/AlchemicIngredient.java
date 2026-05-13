package alchemy;

import be.kuleuven.cs.som.annotate.*;
import exceptions.*;
import alchemy.*;

import java.util.ArrayList;

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
     *        The given ingredient type of the alchemic ingredient
     * @param amount
     *        The given amount of the alchemic ingredient
     * @param unit
     *        The given unit of the quantity of the alchemic ingredient
     */
    public void AlchemicIngredient( IngredientType ingredientType, int amount, UnitOfQuantity unit) throws IllegalNameException {
        setFullName(null);
        setIngredientType(ingredientType);
        //this.state = ingredientType.getStdState();
        setQuantity(amount, unit);
        //this.temperature = ingredientType.getStdTemp();
        //container
    }



    /**********************************************************
     * Name - Defensive programming
     **********************************************************/

    /**
     * Variable referencing the full name of the alchemic ingredient.
     */
    private String fullName;

    /**
     * Variable referencing the special name of the alchemic ingredient.
     */
    private String specialName = null;

    /**
     * Return the simple name of this alchemic ingredient.
     * @note This is equal to the name of the ingredient type.
     */
    public String getSimpleName() {
        return this.getIngredientType().getName();
    }

    /**
     * Return the full name of this alchemic ingredient.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Return the special name of this alchemic ingredient.
     */
    public String getSpecialName() {
        return specialName;
    }

    /**
     *
     *
     * @return
     */
    public String getName() {
        if (this.getSpecialName() != null){
            return (this.getSpecialName() + "(" + this.getFullName() + ")" );
        }
        return this.getFullName();
    }

    /**
     * Set the full name of this alchemic ingredient to the given name.
     *
     * @param   fullName
     *          The given name to be set as the full name
     * @effect  If the given name is a null-pointer, the full name of this alchemic ingredient is
     *          set to the simple name of this alchemic ingredient (the name of the type of ingredient).
     *          The full name of this alchemic ingredient is otherwise set to the given name.
     *          | fullName == null
     *          | XCBHIQEWVEFIYPVCBEWHI;VBFEWHI;EWVBUIV;WEB;UIVE //idk
     */
    protected void setFullName(String fullName) {
        if (fullName == null) {
            this.fullName = getSimpleName();
        }
        else{
            this.fullName = fullName;
        }
    }

    /**
     * Set the special name of this alchemic ingredient to the given name.
     *
     * @param   specialName
     *          The given name
     * @effect  The special name of this alchemic ingredient is set to the given name if
     *          the given name is a valid name, otherwise an exception is thrown.
     *          | if isValidName(specialName)
     *          |   then this.specialName = specialName
     * @throws  IllegalNameException
     *          | ! isValidName(specialName)
     */
    protected void setSpecialName(String specialName) throws IllegalNameException{
        if (IngredientType.isValidName(specialName)) {
            this.specialName = specialName;
        } else {
            throw new IllegalNameException(specialName);
        }
    }
    //--> something is probably wrong, also in the constructor

    /**
     *
     *
     * @param   ingredients
     *
     */
    protected void mixedNames(String[] ingredients){
        int length = ingredients.length;
        if (length < 2){
            //exception
        }else{
            String newName = ingredients[0] + " mixed with " + ingredients[1];
            for (int i = 2 ; i < length; i++){
                    if (i == length-1){
                        newName = newName +" and " + ingredients[i];
                    }else {
                        newName = newName + ", " + ingredients[i];
                    }
                }
            setFullName(newName);
        }
    }
    //--> only kettle can use this ----> maybe in the MixedIngredient subclass instead?


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
    public State state = ingredientType.getStdState();

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
     * The quantity of the alchemic ingredient expressed in an amount.
     */
    private int quantityAmount;

    /**
     * The quantity of the alchemic ingredient expressed in a unit of quantity.
     */
    private UnitOfQuantity quantityUnit;

    /**
     * Change the quantity of this alchemic ingredient to the
     * given quantity.
     *
     * @param amount
     *        The given amount
     * @param unit
     *        The given unit
     */
    void setQuantity(int amount, UnitOfQuantity unit) {
        this.quantityAmount = amount;
        this.quantityUnit = unit;
    }

    /**
     * Return the numerical quantity of this alchemic ingredient.
     */
    protected int getQuantityAmount() {
        return quantityAmount;
    }

    /**
     * Return the unit of quantity of this alchemic ingredient.
     */
    protected UnitOfQuantity getQuantityUnit() {
        return quantityUnit;
    }



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
     */
    private void setTemperature() {
        this.temperature = ingredientType.getStdTemp();
    }


    //get coldness , get hotness

    // change temp
    // if it has been in the oven or cooler
    // correct input voor temp? --> ingriedientType

    protected boolean canHaveAsStdTemperature(int[] temperature, int maxValue) {
        if (maxValue > Long.MAX_VALUE) {
            return false;
        }
        if (temperature.length != 2) {
            return false;
        }
        int coldness = temperature[0];
        int hotness = temperature[1];

        if (coldness < 0 || coldness > maxValue) {
            return false;
        }

        if (hotness < 0 || hotness > maxValue) {
            return false;
        }

        if (coldness != 0 && hotness != 0) {
            return false;
        }
        return true;
    }



}
