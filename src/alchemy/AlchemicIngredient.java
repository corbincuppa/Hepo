package alchemy;

import be.kuleuven.cs.som.annotate.*;
import exceptions.*;

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
        setFullName();
        setIngredientType(ingredientType);
        this.state = ingredientType.getStdState();
        setQuantity(amount, unit);
        setTemperature();
    }



    /**********************************************************
     * Name - Defensive programming
     **********************************************************/

    /**
     * Variable referencing the full name of the alchemic ingredient.
     */
    private String fullName = null;

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
     * @effect  If the given name is a null-pointer, the full name of this alchemic ingredient is
     *          set to the simple name of this alchemic ingredient (the name of the type of ingredient).
     *          The full name of this alchemic ingredient is otherwise set to the given name.
     *          | fullName == null
     *          | XCBHIQEWVEFIYPVCBEWHI;VBFEWHI;EWVBUIV;WEB;UIVE //idk
     */
    protected void setFullName() {
            this.fullName = getSimpleName();
    }

    protected void changeFullName(String name){
        if (name == null) {
            this.fullName = getSimpleName();
        }
        else{
            this.fullName = name;
        }
    }


    /**
     * Check whether the given character is a valid character for the name of this ingredient type
     *
     * @param   character
     *          The given character to be checked
     * @return  True if the given character is equal to the backwards slash, the open bracket or closed bracket,
     *          false otherwise.
     *          | character == '\'' || character == '(' || character == ')'
     */
    private boolean acceptableSymbols(Character character){
        // is dat de bedoeling:   '\''   ?
        if (character == '\'' || character == '(' || character == ')') {
            return true;
        }
        return false;
    }

    /**
     * Check whether the rest of the word (without the first uppercase letter) is a valid word.
     *
     * @param   word
     *          The given word to be checked
     * @param   index
     *          The index at which to start so unnecessary beginning of word isn't checked
     * @return  True if the rest of the word is compiled of acceptable characters, false if the
     *          rest of the word container an uppercase letter.
     *          | i dont know??
     */
    private boolean restWithLowercases(String word, int index) {
        for (int i = index; i < word.length(); i++) {
            char c = word.charAt(i);
            if (acceptableSymbols(c)){
                i ++;
            }
            if (!Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the word
     *
     * @param word
     * @return
     */
    private boolean startsUppercaseRestLower(String word) {
        char first = word.charAt(0);
        if (Character.isLetter(first)){
            return (Character.isUpperCase(first) && restWithLowercases(word, 1));
        }
        if (acceptableSymbols(first)){
            char second = word.charAt(1);
            return (Character.isUpperCase(second) && restWithLowercases(word, 2));
        }
        return false;
    }

    // deze paar functies die te maken hebben met een validname moet je me een keer uitleggen want mn brein werkt op dit moment niet
    protected String[] letters(String word){
        String[] letters = new String[word.length()];
        for (int i = 0 ; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isLetter(c)){
                letters[i] = String.valueOf(c);
            }
        }
        return letters;
    }

    /**
     * Check whether the given name is a legal name for an ingredient type.
     *
     * @param  	name
     *			The name to be checked
     * @return
     */
    @Raw
    protected boolean isSpecialNameValid(String name) {
        if (name == null || name.isEmpty() || name.toLowerCase().contains("mixed") || name.toLowerCase().contains("with")) {
            return false;
        }
        String[] words = name.split(" ");
        if (words.length == 1) {
            if (letters(words[0]).length < 3) {
                return false;
            } else {
                return startsUppercaseRestLower(words[0]);
            }
        }
        for (String word : words) {
            if (letters(word).length < 2) {
                return false;
            } else {
                if (!startsUppercaseRestLower(word)){
                    return false;
                }
            }
        }
        return true;
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
        //instanceof ingredient type mixed
        if (isSpecialNameValid(specialName)) {
            this.specialName = specialName;
        } else {
            throw new IllegalNameException(specialName);
        }
    }
    //--> alleen mixed kan speciale naam hebben

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
            changeFullName(newName);
        }
    }
    //--> only kettle can use this ----> maybe in the MixedIngredient subclass instead?

    /**
     * Add "Heated" to the full name of the given alchemic ingredient.
     *
     */
    protected void addPrefixHeated(){
        String newName = "Heated" + this.getFullName();
        this.changeFullName(newName);
    }
    // only oven can use this

    /**
     * Add "Cooled" to the full name of the given alchemic ingredient.
     *
     */
    protected void addPrefixCooled(){
        String newName = "Cooled" + this.getFullName();
        this.changeFullName(newName);
    }
    // only coolingbox can use this

    protected void addPrefixState(State state){
        String prefix = "Powdered";
        if (state == State.LIQUID)
            prefix = "Liquid";
        String newName = prefix + this.getFullName();
        this.changeFullName(newName);
    }
    // only transmogrifier can use this


    /**********************************************************
     * IngredientType
     **********************************************************/
    //      THIS OR THE ALCHEMIC INGREDIENT? PERIOD OR NO PERIOD FOR JAVADOC COMMENTS?????????????
    /**
     * The ingredient type of the alchemic ingredient.
     */
    private IngredientType ingredientType = null;

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
    // --> moet er getest worden of ingredientType bestaat?

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
    private void changeState(State state) {
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
    private int[] temperature = null;

    /**
     * Change the curren temperature of this alchemic ingredient to the
     * given temperature.
     *
     */
    private void setTemperature() { this.temperature = ingredientType.getStdTemp();;
    }

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

    protected void changeTemperature(int[]temp) {
        if (canHaveAsStdTemperature(temp, 10000)) {
            this.temperature = temp;
        }
    }
    // if it has been in the oven or cooler

    public int[] getTemperature(){
        return temperature;
    }

    public int getColdness(){
        return temperature[0];
    }

    public int getHotness(){
        return temperature[1];
    }

}
