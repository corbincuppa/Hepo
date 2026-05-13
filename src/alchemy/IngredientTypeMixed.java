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
    protected IngredientTypeMixed(String name, State stdState, int[] stdTemp) throws IllegalNameException {
        super(name, stdState, stdTemp);
    }

    /**********************************************************
     * Name
     **********************************************************/

    // mixed and with moeten niet met hoofletter beginnen

    /**
     * Check whether the given name is a legal name for an ingredient type.
     *
     * @param  	name
     *			The name to be checked
     * @return
     */
    @Override
    protected boolean canHaveAsName(String name) {
        if (name == null || name.isEmpty()) {
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


}
