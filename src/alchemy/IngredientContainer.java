package alchemy;

/**
 * A class of ingredient containers.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public class IngredientContainer {
    /**********************************************************
     * Capacity
     **********************************************************/
    private String capacity;

    /**********************************************************
     * Ingredient
     **********************************************************/
    private AlchemicIngredient ingredient;

    protected AlchemicIngredient getIngredient() {
        return this.ingredient;
    }
    /**********************************************************
     * Constructors
     **********************************************************/
    public void IngredientContainer(String capacity, AlchemicIngredient ingredient) {
        this.capacity = capacity;
        this.ingredient = ingredient;
    }

}
