package alchemy;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of ingredient types.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

//invars??????? :(

public class IngredientType {

    /**********************************************************
     * Constructors
     **********************************************************/
    /**
     * Initialize a new ingredient type with a given name, standard state and standard temperature.
     *
     * @param  name
     *         The given name
     * @param  stdState
     *         The given standard temperature of the ingredient type.
     * @param  stdTemp
     *         The given standard temperature of the ingredient type.
     */
    protected IngredientType(String name, State stdState, int[] stdTemp) {
        setName(name);
        this.stdState = stdState;
        setTemp(stdTemp);
    }



    /**********************************************************
     * IngredientType
     **********************************************************/

    // ???? why is header called ingredient type
    IngredientType water = new IngredientType("Water", State.LIQUID, new int[]{0, 20});

    /**********************************************************
     * Name
     **********************************************************/

    /**
     * Variable referencing the name of this alchemic ingredient.
     */
    private String name = getDefaultName();

    /**
     * Return the name for a new disk item which is to be used when the
     * given name is not valid.
     *
     * @return	A valid disk item name.
     *         	| canHaveAsName(result) && result.equals("ingredient_type"")
     */
    @Model
    protected String getDefaultName() {
        return "ingredient_type";
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
    private static boolean acceptableSymbols(Character character){
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

    /**
     * Check if the word
     *
     * @param word
     * @return
     */
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

    // deze paar functies die te maken hebben met een validname moet je me een keer uitleggen want mn brein werkt op dit moment niet
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
    @Raw
    protected static boolean canHaveAsName(String name) {
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
     *          | if (canHaveAsName(name))
     *          | then new.getName().equals(name)
     *          | else new.getName().equals(getDefaultName())
     */
    @Raw @Model
    private void setName(String name) {
        if (canHaveAsName(name)) {
            this.name = name;
        } else {
            this.name = getDefaultName();
        }
    }

    /**
     * Return the name of this ingredient type.
     */
    @Raw @Basic
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

    /**
     * Return whether the given type is a valid standard state for an ingredient type.
     *
     * @param  stdState
     *         The standard state to check.
     * @return True if and only if the given standard state is effective.
     *         | result == (stdState != null)
     */
    public static boolean isValidStdState(State stdState){
        return stdState != null;
    }
    // bij vorige practicum was er zoiets bij type -> is het hier ook nodig

    /**
     * Return the standard state of this ingredient type.
     */
    @Raw @Basic @Immutable
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
    private int[] stdTemp = null;


    /**
     * Return the default temperature.
     */
    private static int[] getDefaultTemp() {
        return new int[]{0, 20};
    }


    /**
     * Return the standard temperature of this ingredient type.
     */
    public int[] getStdTemp() {
        return stdTemp;
    }

    /**
     * Check if the given temperature is a valid temperature
     *
     * @param temperature
     * @param maxValue
     * @return
     */
    protected boolean canHaveAsStdTemperature(int[] temperature, int maxValue) {
        if (maxValue > Long.MAX_VALUE) {
            return false;
        }
        if (temperature.length != 2) {
            return false;
        }
        int coldness = temperature[0];
        int hotness = temperature[1];

        if (coldness != 0 ) {
            return false;
        }

        if (hotness <= 0 || hotness > maxValue) {
            return false;
        }
        return true;
    }
    // std moet strikt warmer zijn dan [0,0]


    /**
     * Set the temperature of this ingredient type to the given temperature
     *
     * @param   temp
     *          The given temperature to be set
     * @effect  If the given temperature is a valid temperature and doesn't go above 10 000,
     *          then the standard temperature of this ingredient type is set to the given temperature.
     *          | isValidTemperature(temp, 10 000)
     *          |   then this.stdTemp = temp
     * @effect  If the given temperature is not a valid temperature, then the standard temperature of this
     *          ingredient type is set to the default temperature.
     *          | ! isValidTemperature(temp, 10 000)
     *          |   then this.stdTemp = getDefaultTemp()
     */
    public void setTemp(int[] temp) {
        if (canHaveAsStdTemperature(temp, 10000)){
            this.stdTemp = temp;
        }else{
            this.stdTemp = getDefaultTemp();
        }
    }
}