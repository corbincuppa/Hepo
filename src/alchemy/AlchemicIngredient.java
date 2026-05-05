package alchemy;

import be.kuleuven.cs.som.annotate.*;
import exceptions.*;
import alchemy.*;

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
    public void AlchemicIngredient( IngredientType ingredientType, int quantity) throws IllegalNameException {
        setFullName(null);
        setIngredientType(ingredientType);
        //this.state = ingredientType.getStdState();
        setQuantity(quantity);
        //this.temperature = ingredientType.getStdTemp();
    }



    /**********************************************************
     * Name - Defensive programming
     **********************************************************/
    private String fullName;
    private String specialName = null;

    public String getSimpleName() {
        return this.getIngredientType().getName();
    }

    public String getFullName() {
        return fullName;
    }

    public String getSpecialName() {
        return specialName;
    }

    public String getName() {
        if (this.getSpecialName() != null){
            return (this.getSpecialName() + "(" + this.getFullName() + ")" );
        }
        return this.getFullName();
    }

    protected void setFullName(String fullName) {
        if (fullName == null) {
            this.fullName = getSimpleName();
        }
        else{
            this.fullName = fullName;
        }
    }


    //only oven can use this
    protected void addPrefixHeated(){
        String newName = "Heated" + this.getFullName();
        setFullName(newName);
    }

    //only cooler can use this
    protected void addPrefixCooled(){
        String newName = "Cooled" + this.getFullName();
        setFullName(newName);
    }

    protected void setSpecialName(String specialName) throws IllegalNameException{
        if (IngredientType.isValidName(specialName)) {
            this.specialName = specialName;
        } else {
            throw new IllegalNameException(specialName);
        }
    }
    //--> something is probably wrong, also in the constructor

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
    //--> only kettle can use this


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
        return quantity;
    }



    /**********************************************************
     * Temperature
     **********************************************************/
    /**
     * The current temperature of the alchemic ingredient.
     */
    private int[] temperature = ingredientType.getStdTemp();

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
    // correct input voor temp? --> ingriedientType



}
