package alchemy;

import be.kuleuven.cs.som.annotate.*;
import exceptions.*;

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
    public AlchemicIngredient( IngredientType ingredientType, int amount, UnitOfQuantity unit) {
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
     * Get the full name of this alchemic ingredient.
     *
     * @return  The special name with the full name between brackets of this ingredient is the special name is not null,
     *          otherwise the full name of this alchemic ingredient.
     *          | if (this.getSpecialName() != null) then result == this.getSpecialName() +
     *          |        "(" + this.getFullName() + ")"
     *          | else result == this.getFullName()
     */
    public String getName() {
        if (this.getSpecialName() != null){
            return (this.getSpecialName() + "(" + this.getFullName() + ")" );
        }
        return this.getFullName();
    }

    /**
     * Set the full name of this alchemic ingredient.
     *
     * @post    The full name of this alchemic ingredient is set to the simple name
     *          | this.fullName = getSimpleName()
     */
    protected void setFullName() {
            this.fullName = getSimpleName();
    }

    /**
     * Set the full name of this alcheic ingredient to the given name.
     *
     * @param   name
     *          The given name
     * @post    The full name is set to the given name.
     *          | this.fullName = name
     */
    protected void changeFullName(String name){
        this.fullName = name;
    }


    /**
     * Check whether the given character is a valid character for the name of this ingredient type
     *
     * @param   character
     *          The given character to be checked
     * @return  True if the given character is equal to the backwards slash, the open bracket or closed bracket,
     *          false otherwise.
     *          | result = (character == '\'' || character == '(' || character == ')')
     */
    private boolean acceptableSymbols(Character character){
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
     *          The index at which to start so the unnecessary beginning of the word up until the index isn't checked
     * @return  True if the rest of the word is compiled of acceptable characters, false if the
     *          rest of the word contains an uppercase letter.
     *          | for each i in index..word.length()
     *          |   if (acceptableSymbols(word.charAt(i)))
     *          |       i++
     *          |   else if (!Character.isLowerCase(word.charAt(i)))
     *          |       result == false
     *          | result == true
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
     * Check if the given word starts with an uppercase letter and the rest is lower case.
     *
     * @param   word
     *          The given word to be checked
     * @return  If the first character of the word is a letter, then it is checked if that character is uppercase and if
     *          the rest of the word without that character consists of lowercase letters.
     *          If the first character of the word is instead an accepted symbol, then it is checked if the second character
     *          is an uppercase letter and the rest of the word without those two characters consister of lowercase letters.
     *          False otherwise.
     *          | if (Character.isLetter(word.charAt(0)))
     *          |   then result == (Character.isUpperCase(word.charAt(0)) && restWithLowercases(word, 1))
     *          | if (acceptableSymbols(word.charAt(1)))
     *          |   then result == (Character.isUpperCase(word.charAt(1)) && restWithLowercases(word, 2))
     *          | else result == false
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

    /**
     * Returns the letters of a given word.
     *
     * @param   word
     *          The given word
     * @effect  For each character in the given word, if that character is a letter, then it is appended to the String
     *          of letter which is to be returned.
     *          | for each i in 0..word.length()
     *          |   if (Character.isLetter(word.charAt(i)))
     *          |       then letters[i] == String.valueOf(word.charAt(i))
     * @return  The list of letter in the given word.
     *          | result == letters
     */
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
     * @return  False if name is null, empty, contains the word "mixed" or contains the word "with".
     *          False if the name contains but one word, and if then that word is lesser than 3 characters long, else
     *          it is checked if the word starts with an uppercase letter and otherwise lowercase letters.
     *          False if any word in the name consists of less than two characters.
     *          False if any word in the name consists of more than two characters but it does not start with an
     *          uppercase letters.
     *          True otherwise.
     *          | if (name == null || name.isEmpty() ||
     *          |       name.toLowerCase().contains("mixed") || name.toLowerCase().contains("with"))
     *          |   then result == false
     *          |
     *          | if (words.length == 1) then
     *          |   if (letters(words[0]).length < 3) then
     *          |       result == false
     *          |   else result == tartsUppercaseRestLower(words[0])
     *          |
     *          | for each word in words
     *          |   if (letters(word).length < 2) then result == false
     *          |   else if (!startsUppercaseRestLower(word)) then result == false
     *          |
     *          | result == true
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
    protected void setSpecialName(String specialName) throws IllegalArgumentException, IllegalNameException{
        if (!(this.getIngredientType() instanceof IngredientTypeMixed)) {
            throw new IllegalArgumentException("setSpecialName is permitted only for IngredientTypeMixed. Current type: " + this.getIngredientType());
        }
        if (isSpecialNameValid(specialName)) {
            this.specialName = specialName;
        } else {
            throw new IllegalNameException(specialName);
        }
    }

    /**
     *
     *
     * @param   ingredients
     *
     */
    protected String mixedNames(String[] ingredients){
        int length = ingredients.length;
        if (length < 2){
            //exception --> hier of bij kettle als er maar 1 ingredient erin zit
            // of miss hoeft er geen exception
            // 1 ingredient in kettle --> gewoon die ingredient terug
        }else{
            String newName = ingredients[0] + " mixed with " + ingredients[1];
            for (int i = 2 ; i < length; i++){
                    if (i == length-1){
                        newName = newName +" and " + ingredients[i];
                    }else {
                        newName = newName + ", " + ingredients[i];
                    }
                }
            return newName;
        }
    }
    //--> only kettle can use this
    // should this return the name instead? --> Miss wel en bij kettle steken zodat het de naam van de new IngredientType wordt

    /**
     * Add "Heated" to the full name of this alchemic ingredient.
     *
     * @post    The full name of this alchemic ingredient is changed by adding "Heated" before it.
     *          | this.changeFullName("Heated " + this.getFullName())
     */
    protected void addPrefixHeated(){
        String newName = "Heated " + this.getFullName();
        this.changeFullName(newName);
    }

    /**
     * Add "Cooled" to the full name of this alchemic ingredient.
     *
     * @post    The full name of this alchemic ingredient is changed by adding "Cooled" before it.
     *          | this.changeFullName("Cooled " + this.getFullName())
     */
    protected void addPrefixCooled(){
        String newName = "Cooled " + this.getFullName();
        this.changeFullName(newName);
    }

    /**
     * Add the needed prefix to the name of this alchemic ingredient based on the given state.
     *
     * @param   state
     *          The given state
     * @post    If the given state is State.LIQUID, "Liquid" will be added before the full name of this ingredient,
     *          otherwise "Powdered" will be added.
     *          | if (state == State.LIQUID)
     *          |   then prefix = "Liquid"
     *          | else prefix = "Powdered"
     *          |
     *          | this.changeFullName(prefix + this.getFullName())
     */
    protected void addPrefixState(State state){
        String prefix = "Powdered";
        if (state == State.LIQUID)
            prefix = "Liquid";
        String newName = prefix + this.getFullName();
        this.changeFullName(newName);
    }


    /**********************************************************
     * IngredientType
     **********************************************************/

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

    /**
     * Returns the ingredient type of this alchemic ingredient.
     */
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
    protected void changeState(State state) {
        this.state = state;
    }

    /**
     * Return the state of this alchemic ingredient.
     */
    public State getState() {
        return state;
    }



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
     * Change the current temperature of this alchemic ingredient to the
     * given temperature.
     *
     */
    private void setTemperature() { this.temperature = ingredientType.getStdTemp();;
    }

    /**
     * Check if the given temperature is a valid temperature for an alchemic ingredient given a maximum value.
     *
     * @param   temperature
     *          The given temperature to be checked
     * @param   maxValue
     *          The given maximum value for the temperature
     * @return  False if the given maximum value is greater than the Java MAX_VALUE.
     *          False if the given temperature expressed as an array is not composed of two elements (its size is not 2).
     *          False if the coldness of the temperature is lesser than 0 or if the coldness is greater than the given
     *          maximum value.
     *          False if the hotness of the temperature is lesser than 0 or if the hotness is greater than the given
     *          maximum value.
     *          False of the coldness of the temperature is not 0 and the hotness of the temperature is not 0,
     *          true otherwise.
     *          | if (maxValue > Long.MAX_VALUE) then result == false else
     *          | if (temperature.length != 2) then result == false else
     *          | if (coldness < 0 || coldness > maxValue) then result == false else
     *          | if (hotness < 0 || hotness > maxValue) then result == false else
     *          | if (coldness != 0 && hotness != 0) then result == false else
     *          | result == true
     */
    protected boolean canHaveAsTemperature(int[] temperature, int maxValue) {
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

    /**
     * Set the temperature of this alchemic ingredient to the given temperature.
     *
     * @param   temp
     *          The given temperature
     * @post    If the given temperature is a valid temperature then the temperature of this alchemic
     *          ingredient is set to the given temperature.
     *          | canHaveAsStdTemperature(temp, 10 000)
     */
    protected void changeTemperature(int[] temp) {
        if (canHaveAsTemperature(temp, 10000)) {
            this.temperature = temp;
        }
    }

    /**
     * Returns the temperature of this alchemic ingredient.
     */
    public int[] getTemperature(){
        return temperature;
    }

    /**
     * Returns the coldness of the temperature of this alchemic ingredient.
     */
    public int getColdness(){
        return temperature[0];
    }

    /**
     * Returns the hotness of the temperature of this alchemic ingredient
     */
    public int getHotness(){
        return temperature[1];
    }

}
