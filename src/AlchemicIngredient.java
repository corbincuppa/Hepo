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

    private boolean startsWithUppercase(String word) {
        char first = word.charAt(0);
        if (Character.isLetter(first)){
            return Character.isUpperCase(first);
        }
        if (first == '\'' || first == '(' || first == ')'){
            char second = word.charAt(1);
            return Character.isUpperCase(second);
        }
        return false;
    }

    private boolean restWithLowercases(String word) {
        for (int i = 1; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }// wordt '() als lowercase gezien?

    private static boolean isValidCharacters(String word) {
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c) || c == '\'' || c == '(' || c == ')') {
                return true;
            }
        } return false;
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
        } else {
            String[] words = name.split(" ");
            if (words.length == 1) {
                if (words[0].length() < 3) {
                    return false;
                } else {
                    //  checking characters
                }
            } else {
                for (String word : words) {
                    if (words[0].length() < 2) {
                        return false;
                    } else {
                        // checking characters
                    }
                }
                return true;
            }
        }
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
