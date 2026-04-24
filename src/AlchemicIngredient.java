import be.kuleuven.cs.som.annotate.*;

/**
 * A class of alchemic ingredients.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public class AlchemicIngredient {
    /**********************************************************
     * Name - Defensive programming
     **********************************************************/

    /**
     * Variable referencing the name of this alchemic ingredients.
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
     * Check whether the given name is a legal name for an alchemic ingredients.
     *
     * @param  	name
     *			The name to be checked
     * @return
     */
    private boolean isValidName(String name) {
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
     * Set the name of this alchemic ingredients to the given name.
     *
     * @param   name
     * 			The new name for this alchemic ingredients.
     * @post    If the given name is valid, the name of
     *          this alchemic ingredients is set to the given name,
     *          otherwise !!!!exception!!!!!!.
     *          | if (isValidName(name))
     *          |      then new.getName().equals(name)
     *          |      else throws exception++++++++++
     */
    @Raw @Model
    private void setName(String name) {
        if (isValidName(name)) {
            this.name = name;
        } else {
            //exception;
        }
    }


    /**********************************************************
     * IngredientType
     **********************************************************/

    /**********************************************************
     * State
     **********************************************************/

    /**********************************************************
     * Quantity
     **********************************************************/

    /**********************************************************
     * Temperature
     **********************************************************/

    /**********************************************************
     * Constructors
     **********************************************************/

}
