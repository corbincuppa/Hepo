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
     *          | result == (character == '\'' || character == '(' || character == ')')
     */
    private static boolean acceptableSymbols(Character character){
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