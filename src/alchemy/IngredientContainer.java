package alchemy;

import be.kuleuven.cs.som.annotate.*;

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
     *          ingredient and the state of the given capacity are equal, and
     *          if then the quantity of the given alchemic ingredient expressed in spoons is lesser than or equal to
     *          the given capacity expressed in spoons, then the capacity of the initialized container is set to
     *          the given capacity.
     *          | if (isValidCapacity(capacity) && ingredient.getQuantityUnit().getState() == capacity.getState())
     *          |   then setCapacity(capacity)
     * @effect  If the given capacity is valid and the state of the quantity of the given alchemic
     *          ingredient and the state of the given capacity are equal, and
     *          if then the quantity of the given alchemic ingredient expressed in spoons is lesser than or equal to
     *          the given capacity expressed in spoons, then the contents of the initialized container are set to the
     *          given ingredient.
     *          | if (isValidCapacity(capacity) && ingredient.getQuantityUnit().getState() == capacity.getState())
     *          |   then setContents(ingredient)
     */
    public IngredientContainer(UnitOfQuantity capacity, AlchemicIngredient ingredient) {
           setCapacity(capacity);
           setContents(ingredient);
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
        return capacity;
    }

    /**
     * Check is the given capacity is a valid capacity for an ingredient container
     *
     * @param   capacity1
     *          The given capacity to be checked
     * @return  True if the given capacity is not a DROP, PINCH or STOREROOM, false otherwise.
     *          | result ==
     *          |    ! (capacity1 == UnitOfQuantity.PINCH || capacity1 == UnitOfQuantity.DROP
     *                 || capacity1 == UnitOfQuantity.STOREROOM)
     */
    @Model
    protected boolean isValidCapacity(UnitOfQuantity capacity1) {
        // Check that it is not drop(), pinch() or storeroom()
        if (capacity1 == UnitOfQuantity.PINCH || capacity1 == UnitOfQuantity.DROP
                || capacity1 == UnitOfQuantity.STOREROOM) {
            return false;
        }
        return true;
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
        return contents;
    }

    /**
     * Set the contents of this container to the given alchemic ingredient.
     *
     * @pre     The states of the capacity of this container and the state of the unit of quantity of the
     *          given alchemic ingredient must be the same or one of them must have both states.
     *          | quantityUnit.getState() == capacity.getState() || capacity.getState() == State.BOTH || quantityUnit.getState() == State.BOTH
     * @pre
     * @param   ingredient
     *          The given alchemic ingredient to be stored inside this container.
     */
    private void setContents(AlchemicIngredient ingredient) {
        int amount = ingredient.getQuantityAmount();
        UnitOfQuantity quantityUnit = ingredient.getQuantityUnit();
        if( isValidQuantity(amount, quantityUnit) ){
            this.contents = ingredient;
        }
    }

    /**
     * Check whether the given quantity is a valid quantity for an ingredient container.
     *
     * @param   quantityAmount
     *          The given amount of quantity to be checked
     * @param   quantityUnit
     *          The given unit of quantity to be checked
     * @return  True if the states of the given unit of quantity and of the capacity are the same or if one of them
     *          has both states and if the given amount of quantity expressed in spoons is lesser than or equal to
     *          the capacity of this container expressed in spoons. False otherwise.
     *          | result ==
     *          |   quantityUnit.getState() == capacity.getState() || capacity.getState() == State.BOTH
     *          |       || quantityUnit.getState() == State.BOTH
     *          |   && quantityUnit.getAmountSpoons() <= capacity.getAmountSpoons()
     */
    @Model
    private boolean isValidQuantity(int quantityAmount, UnitOfQuantity quantityUnit) {
        // Set the capacity and unit of quantity into amount of spoons
        double unitSpoons = quantityUnit.getAmountSpoons();
        double capacitySpoons = capacity.getAmountSpoons();

        // Check if the states of both units are the same or if one of them has both states
        if (quantityUnit.getState() == capacity.getState() || capacity.getState() == State.BOTH || quantityUnit.getState() == State.BOTH) {
            if (unitSpoons <= capacitySpoons) {
                return true;
            }
            if (unitSpoons > capacitySpoons) {
                return false;
            }
        }
        return false;
    }

