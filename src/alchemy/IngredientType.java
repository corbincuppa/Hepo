package alchemy;

import be.kuleuven.cs.som.annotate.*;
import exceptions.*;

/**
 * A class of ingredient types.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

//invars???????

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
    protected IngredientType(String name, State stdState, int[] stdTemp) throws IllegalNameException {
        setName(name);
        this.stdState = stdState;
        this.stdTemp = stdTemp;
    }
    //klopt dit?


    /**********************************************************
     * IngredientType
     **********************************************************/
    IngredientType water = new IngredientType("Water", State.LIQUID, new int[]{0, 20});

    /**********************************************************
     * Name
     **********************************************************/

    /**
     * Variable referencing the name of this alchemic ingredient.
     */
    private String name = null;

    private static boolean acceptableSymbols(Character character){
        if (character == '\'' || character == '(' || character == ')') {
            return true;
        }
        return false;
    }

    private static boolean restWithLowercases(String word, int index) {
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

    private static boolean startsUppercaseRestLower(String word) {
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

    protected static String[] letters(String word){
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
    protected static boolean isValidName(String name) {
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
     * Set the name of this ingredient type to the given name.
     *
     * @param   name
     * 			The new name for this ingredient type.
     * @post    If the given name is valid, the name of
     *          this ingredient type is set to the given name,
     *          otherwise it throws an IllegalNameException
     *          | if (isValidName(name))
     *          |      then new.getName().equals(name)
     *          |      else throws IllegalNameException
     * @throws IllegalNameException [must]
     * 	       The given name is not a legal name for any ingredient type
     * 	       | (! isValidName(name)
     */
    @Raw
    @Model
    private void setName(String name) throws IllegalNameException {
        if (isValidName(name)) {
            this.name = name;
        } else {
            throw new IllegalNameException(name);
        }
    }
    //  ingredienttype --> total (exception needs to be caught)

    public String getName() {
        return name;
    }


    /**********************************************************
     * Standard state
     **********************************************************/

    /**
     * Variable referencing the standard state of the ingredient type.
     */
    private final State stdState;

    public State getStdState() {
        return stdState;
    }

    /**********************************************************
     * Standard temperature
     **********************************************************/

    /**
     * Variable referencing the standard temperature of the ingredient type.
     *
     * @note The first integer refers to the coldness and the second integer to the hotness.
     */
    private int[] stdTemp;

    public int[] getStdTemp() {
        return stdTemp;
    }
    public int getStdColdness(){return (this.getStdTemp())[0];}
    public int getStdHotness(){return (this.getStdTemp())[1];}
    //--> niet specifiek voor std


    protected boolean isValidTemperature(int[] temperature, int maxValue) {
        if (maxValue > Long.MAX_VALUE) {
            return false;
        }
        if (temperature.length != 2) {
            return false;
        }
        int coldness = temperature[0];  //getStdColdness
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