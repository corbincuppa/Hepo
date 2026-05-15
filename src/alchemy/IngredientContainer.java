package alchemy;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of ingredient containers.
 *
 * @invar   The capacity of an ingredient container must be a valid capacity.
 *          | isValidCapacity(capacity)
 * @invar   The quantity of a given ingredient inside a container must be a valid quantity.
 *          | isValidQuantity(ingredient.getQuantity())
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
     * @pre     The given capacity must be a valid capacity for an ingredient container.
     *          | isValidCapacity(capacity)
     * @pre     The quantity of the given alchemic ingredient must be a valid quantity for an ingredient container.
     *          | isValidQuantity(ingredient.getQuantityAmount(), ingredient.getQuantityUnit())
     */
    public IngredientContainer(UnitOfQuantity capacity, AlchemicIngredient ingredient) {
        setCapacity(capacity);
        setContents(ingredient);
    }

    /**
     * Initialize a container with a given alchemic ingredient which fits the given ingredient the best
     * in terms of size.
     *
     * @param   ingredient
     *          The given alchemic ingredient to be stored in a best fitting container
     * @pre     The given capacity must be a valid capacity for an ingredient container.
     *          | isValidCapacity(capacity)
     * @pre     The quantity of the given alchemic ingredient must be a valid quantity for an ingredient container.
     *          | isValidQuantity(ingredient.getQuantityAmount(), ingredient.getQuantityUnit())
     */
    public IngredientContainer(AlchemicIngredient ingredient) {
        State state = ingredient.getState();
        int amount = ingredient.getQuantityAmount();
        UnitOfQuantity unit1 = ingredient.getQuantityUnit();
        double inSpoons = unit1.getAmountSpoons() * amount;
        UnitOfQuantity capacity = UnitOfQuantity.getBestUnit(inSpoons, state);
        setCapacity(capacity);
        setContents(ingredient);
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
     * @param   capacity1 The given capacity to be checked
     * @return  True if the given capacity is not a DROP, PINCH or STOREROOM, false otherwise.
     *          | result ==
     *          |    ! (capacity1 == UnitOfQuantity.PINCH || capacity1 == UnitOfQuantity.DROP
     *          |       || capacity1 == UnitOfQuantity.STOREROOM)
     */
    @Model
    protected static boolean isValidCapacity(UnitOfQuantity capacity1) {
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
     * @param   capacity The given capacity expressed as one unit of quantity
     * @effect  The capacity of this container is set to the given capacity if the given
     *          capacity is a valid capacity.
     *          | if isValidCapacity(capacity)
     *          |   then this.capacity = capacity
     * @throws  IllegalArgumentException
     *          | ! isValidCapacity(capacity)
     */
    @Model
    private void setCapacity(UnitOfQuantity capacity) throws IllegalArgumentException {
        if (isValidCapacity(capacity)) {
            this.capacity = capacity;
        }
        else{
            throw new IllegalArgumentException("A capacity for a container cannot be the smallest or largest units.");
        }
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
     * @param   ingredient
     *          The given alchemic ingredient to be stored inside this container.
     * @pre     The states of the capacity of this container and the state of the unit of quantity of the
     *          given alchemic ingredient must be the same or one of them must have both states.
     *          | quantityUnit.getState() == capacity.getState() || capacity.getState() == State.BOTH
     *          |   || quantityUnit.getState() == State.BOTH
     * @pre     The unit of quantity expressed in spoons multiplied by the given amount is
     *          lesser than or equal to the capacity of this container expressed in spoons.
     *          | (quantityUnit.getAmountSpoons() * quantityAmount) <= capacity.getAmountSpoons()
     * @throws  IllegalStateException
     *          | isTerminated()
     */
    private void setContents(AlchemicIngredient ingredient) throws IllegalStateException {
        if (this.isTerminated()) {
            throw new IllegalStateException("Object is terminated");
        }

        int amount = ingredient.getQuantityAmount();
        UnitOfQuantity quantityUnit = ingredient.getQuantityUnit();
        if (canHaveAsQuantity(amount, quantityUnit)) {
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
     *          has both states and if the given amount of quantity expressed in spoons multiplied by the given amount is
     *          lesser than or equal to the capacity of this container expressed in spoons. False otherwise.
     *          | result ==
     *          |   quantityUnit.getState() == capacity.getState() || capacity.getState() == State.BOTH
     *          |       || quantityUnit.getState() == State.BOTH
     *          |   && (quantityUnit.getAmountSpoons() * quantityAmount) <= capacity.getAmountSpoons()
     */
    @Model
    protected boolean canHaveAsQuantity(int quantityAmount, UnitOfQuantity quantityUnit) {
        // Set the capacity and unit of quantity into amount of spoons
        double unitSpoons = (quantityUnit.getAmountSpoons() * quantityAmount);
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



    /**********************************************************
     * IsTerminated
     **********************************************************/

    /**
     * Variable referencing whether this ingredient container is terminated.
     */
    private boolean isTerminated = false;

    /**
     * Check if this container is terminated.
     *
     * @return True if this container is terminated, false otherwise.
     */
    private boolean isTerminated() {
        return isTerminated;
    }

    /**
     * Terminate this ingredient container.
     *
     * @effect  The contents of this container is set to null.
     * @effect  The capacity of this container is set to null.
     * @effect  The isTerminated variable is set to true.
     */
    protected void terminate() {
        this.contents = null;
        this.capacity = null;
        isTerminated = true;
    }
}
