package alchemy;

import be.kuleuven.cs.som.annotate.*;

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
     *          The given capacity of the ingredient container.
     * @param   ingredient
     *          The given alchemic ingredient.
     *
     * @effect  If the given capacity is valid and the state of the quantity of the given alchemic
     *          ingredient and the state of the given capacityquantity of the given capacity are equal, and
     *          if then the quantity of the given alchemic ingredient expressed in spoons is lesser than or equal to
     *          the given capacity expressed in spoons, then the capacity of the initialized container is set to
     *          the given capacity.
     *          | if (isValidCapacity(capacity) && ingredient.getQuantityUnit().getState() == capacity.getState())
     *          |   then setCapacity(capacity)
     * @effect  If the given capacity is valid and the state of the quantity of the given alchemic
     *          ingredient and the state of the given capacityquantity of the given capacity are equal, and
     *          if then the quantity of the given alchemic ingredient expressed in spoons is lesser than or equal to
     *          the given capacity expressed in spoons, then the contents of the initialized container are set to the
     *          given ingredient.
     *          | if (isValidCapacity(capacity) && ingredient.getQuantityUnit().getState() == capacity.getState())
     *          |   then setContents(ingredient)
     */
    public IngredientContainer(UnitOfQuantity capacity, AlchemicIngredient ingredient) {
       if (isValidCapacity(capacity) && ingredient.getQuantityUnit().getState() == capacity.getState()) {
           if (ingredient.getQuantityUnit().getAmountSpoons() <= capacity.getAmountSpoons()) {
               setCapacity(capacity);
               setContents(ingredient);
           }
       }
    }

    public void terminate() {

    }


    /**********************************************************
     * Capacity
     **********************************************************/

    /**
     * Variable referencing the capacity of this ingredient container
     * expressed as one unit of quantity.
     */
    private UnitOfQuantity capacity;

    /**
     * Return the capacity of this container.
     */
    public UnitOfQuantity getCapacity() {
        //String returnStr = "1 ";
        //returnStr += capacity;
        //return returnStr;
        return capacity;
    }

    /**
     * Check is the given capacity is a valid capacity for an ingredient container
     *
     * @param   capacity1
     *          The given capacity to be checked
     * @return  True if the given capacity is not a DROP, PINCH or STOREROOM, false otherwise.
     */
    protected boolean isValidCapacity(UnitOfQuantity capacity1) {
        // check that it is not drop(), pinch() or storeroom()
        if (capacity1 == UnitOfQuantity.PINCH || capacity1 == UnitOfQuantity.DROP
                || capacity1 == UnitOfQuantity.STOREROOM) {
            //   throw exception
        }
    }

    /**
     * Set the capacity of this ingredient container ot the given capacity.
     *
     * @param   capacity
     *          The given capacity expressed as one unit of quantity
     *
     * @effect  The capacity of this container is set to the given capacity if the given
     *          capacity is a valid capacity.
     *          | if isValidCapacity(capacity)
     *          |   then this.capacity = capacity
     */
    @Model
    private void setCapacity(UnitOfQuantity capacity) {
        if (isValidCapacity(capacity))
            this.capacity = capacity;
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
