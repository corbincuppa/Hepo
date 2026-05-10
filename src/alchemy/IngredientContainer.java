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
    public IngredientContainer(ArrayList<Object> capacity, AlchemicIngredient ingredient) {
        setContents(ingredient);
        int quantity = ingredient.getQuantity();
        if ( quantity <= capacity) {
            setCapacity(this.capacity.get(0) + quantity);
        }
    }
    // rare constructor
    // type container vragen



    /**********************************************************
     * Capacity
     **********************************************************/

    /**
     * Variable referencing the capacity of this ingredient container
     * expressed in amount of spoons.
     */
    private UnitOfQuantity capacity;

    public String getCapacity() {
        String returnStr = "1 ";
        returnStr += capacity;
        return returnStr;
    }

    private void setCapacity(UnitOfQuantity capacity) {
        this.capacity = capacity;
    }

    protected boolean isValidCapacity(UnitOfQuantity capacity1) {
        // check that it is not drop(), pinch() or storeroom()
        if (capacity1 == UnitOfQuantity.PINCH || capacity1 == UnitOfQuantity.DROP
            || capacity1 == UnitOfQuantity.STOREROOM) {
            //   throw exception
        }
        this.capacity = capacity1;
    }



    /**********************************************************
     * Ingredient
     **********************************************************/

    /**
     * Variable referencing the contents of this container.
     */
    private AlchemicIngredient contents;


    /**
     * Return the ingredient that is in this container.
     */
    protected AlchemicIngredient getIngredient() {
        return (AlchemicIngredient) contents;
    }

    /**
     * Set the contents of this container to the given alchemic ingredient.
     *
     * @param ingredient
     *        The given alchemic ingredient to be stored inside this container.
     */
    private void setContents(AlchemicIngredient ingredient) {
        int quantity = ingredient.getQuantity();
        UnitOfQuantity quantityUnit = ingredient.getQuantityUnit();
        if (quantityUnit == capacity) {
            if (quantity > 1) {
                // CAN'T! 2 spoons > 1 spoon
            }

            if (quantity == 1) {
                // goed, maar container is full!
            }
        }
    }

    /**
     * Check whether the given quantity is a valid quantity for an ingredient container.
     *
     * @param quantity
     *        The given quantity to be checked.
     *
     * @return
     */
    private boolean isValidQuantity(int quantity) {

    }



}
