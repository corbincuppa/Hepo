package alchemy;

import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of ingredient types.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public class IngredientType {

    /**********************************************************
     * Constructors
     **********************************************************/
    /**
     * Initialize a new ingredient type with a given name, standard state and standard temperature.
     *
     * @param name
     *        The given name
     * @param stdState
     *        The given standard temperature of the ingredient type.
     * @param stdTemp
     *        The given standard temperature of the ingredient type.
     */
    protected IngredientType(String name, State stdState, int[] stdTemp) {
        setName(name);
        this.stdState = stdState;
        this.stdTemp = stdTemp;
    }



    /**********************************************************
     * IngredientType
     **********************************************************/
    IngredientType water = new IngredientType("water", State.LIQUID, new int[]{0, 20});

    /**********************************************************
     * Name
     **********************************************************/

    /**
     * Variable referencing the name of this alchemic ingredient.
     */
    private String name = null;

    private boolean acceptableSymbols(Character character){
        if (character == '\'' || character == '(' || character == ')') {
            return true;
        }
        return false;
    }

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
     * Check whether the given name is a legal name for an ingredient type.
     *
     * @param  	name
     *			The name to be checked
     * @return
     */
    protected boolean isValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        String[] words = name.split(" ");
        if (words.length == 1) {
            if (words[0].length() < 3) {
                return false;
            } else {
                return startsUppercaseRestLower(words[0]);
            }
        }
        for (String word : words) {
            if (word.length() < 2) {
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
     * Set the name of this ingredient type to the given name.
     *
     * @param   name
     * 			The new name for this ingredient type.
     * @post    If the given name is valid, the name of
     *          this ingredient type is set to the given name,
     *          otherwise !!!!exception!!!!!!.
     *          | if (isValidName(name))
     *          |      then new.getName().equals(name)
     *          |      else throws exception++++++++++
     */
    @Raw
    @Model
    private void setName(String name) {
        if (isValidName(name)) {
            this.name = name;
        } else {
            //exception;
        }
    }

    // CHECK IF EXTRA PREFIXES NEED TO BE ADDED? RETURN SIMPLE OR FULL NAME?
    public String getName() {
        return this.name;
    }



    /**********************************************************
     * Standard state
     **********************************************************/
    // FINAL??
    /**
     * Variable referencing the standard state of the ingredient type.
     */
    private State stdState;

    /**
     * Set the standard state of this ingredient type to the
     * given state.
     *
     * @param stdState
     *        The given standard state
     */
    private void setState(State stdState) {
        this.stdState = stdState;
    }

    /**********************************************************
     * Standard temperature
     **********************************************************/
    // FINAL??
    /**
     * Variable referencing the standard temperature of the ingredient type.
     *
     * @note The first integer refers to the coldness and the second integer to the hotness.
     */
    private int[] stdTemp;
    // Temperature class or ArrayList?


}