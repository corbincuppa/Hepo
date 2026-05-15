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

public class IngredientTypeMixed extends IngredientType{

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
     * @throws IllegalNameException
     *         | !isValidName(name)
     */
    protected IngredientTypeMixed(String name, State stdState, int[] stdTemp){
        super(name, stdState, stdTemp);
    }

    /**********************************************************
     * Name
     **********************************************************/

    /**
     * Return the name for a new mixed ingredient type which is to be used when the
     * given name is not valid.
     *
     * @return	A valid ingredient type name.
     *         	| canHaveAsName(result) && result.equals("Ingredient Type Mixed")
     */
    @Override
    protected String getDefaultName() {
        return "Ingredient Type Mixed";
    }

    /**
     * Check whether the given name is a legal name for a mixed ingredient type.
     *
     * @param  	name
     *			The name to be checked
     * @return  False if name is null, empty.
     *          False if the name contains but one word, and if then that word is lesser than 3 characters long.
     *          False if the word is either "mixed" or "with" and not in lowercase letters.
     *          False if any word in the name consists of less than two characters.
     *          False if any word in the name consists of more than two characters but it does not start with an
     *          uppercase letters.
     *          True otherwise.
     *          | if (name == null || name.isEmpty())
     *          |   then result == false
     *          |
     *          | if (words.length == 1) then
     *          |   if (letters(words[0]).length < 3) then
     *          |       result == false
     *          |   else result == tartsUppercaseRestLower(words[0])
     *          |
     *          | for each word in words
     *          |   if (word != "mixed" && word != "with") then
     *          |         if (letters(word).length < 2)
     *          |          then result == false
     *          |     else if (!startsUppercaseRestLower(word))
     *          |            then result == false
     *          |     else
     *          |         if (!word.equals(word.toLowerCase()))
     *          |             then result == false
     *          | result == true
     */
    @Override
    protected boolean canHaveAsName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        String[] words = name.split(" ");
        if (words.length == 1) {
            return false;
        }
        for (String word : words) {
            if (word.toLowerCase() != "mixed" || word.toLowerCase() != "with"){
                if (letters(word).length < 2) {
                    return false;
                } else {
                    if (!startsUppercaseRestLower(word)){
                        return false;
                    }
                }
            }else{
                if (!word.equals(word.toLowerCase())){
                    return false;
                }
            }
        }
        return true;
    }


}
