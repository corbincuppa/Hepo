package alchemy;

import java.util.ArrayList;

/**
 * A class of ingredient containers.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public class IngredientContainer {

    /**********************************************************
     * Constructors
     **********************************************************/

    /**
     * Initialize a new ingredient container with a given capacity and alchemic ingredient.
     *
     * @param   capacity
     *          The given capacity of the ingredient container expressed in amount of spoons.
     * @param   ingredient
     *          The given alchemic ingredient.
     *
     * @effect  If the given capacity is valid and the quantity of the given
     *          alchemic ingredient is lesser than or equal to the given capacity,
     *          then the capacity of the initialized container is incremented by the quantity.
     *          | if (isValidCapacity(capacity) && quantity <= capacity)
     *          |   then setCapacity(this.capacity + quantity)
     *          \XXXXXXXXXX // IS CORRECT??
     */
    public IngredientContainer(int capacity, AlchemicIngredient ingredient) {
        setContents(ingredient);
        int quantity = getQuantity();
        if (isValidCapacity(capacity) && quantity <= capacity) {
            setCapacity(this.capacity + quantity);
        }
    }



    /**********************************************************
     * Capacity
     **********************************************************/

    /**
     * Variable referencing the capacity of this ingredient container
     * expressed in amount of spoons.
     */
    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    private void setCapacity(int capacity) {
        if (isValidCapacity(capacity))
            this.capacity = capacity;
    }

    /**
     * Check whether the given capacity is a valid capacity for an ingredient container.
     *
     * @param capacity
     *        The given capacity to be checked.
     *
     * @return True is the given capacity is strictly positive, false otherwise.
     */
    private boolean isValidCapacity(int capacity) {
        if (capacity > 0) {
            return true;
        }
        return false;
    }

    /**********************************************************
     * Ingredient
     **********************************************************/

    /**
     * Variable referencing the contents of this container.
     */
    private ArrayList<Object> contents = new ArrayList<Object>();

    /**
     * Return the ingredient that is in this container.
     */
    protected AlchemicIngredient getIngredient() {
        return (AlchemicIngredient) contents.get(0);
    }

    /**
     * Set the contents of this container to the given alchemic ingredient
     * and its quantity.
     *
     * @param ingredient
     *        The given alchemic ingredient to be stored inside this container.
     */
    private void setContents(AlchemicIngredient ingredient) {
        this.contents.add(0, ingredient);
        int quantity = ingredient.getQuantity();
        this.contents.add(1, quantity);
    }

    /**
     * Return the quantity of ingredient inside this container.
     */
    protected int getQuantity() {
        return (int) contents.get(1);
    }


}
