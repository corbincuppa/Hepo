package alchemy;

import exceptions.IllegalAmountException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A class of laboratories.
 *
 * @invar   The capacity of this laboratory must be a valid capacity.
 *          | isValidCapacity()
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public class Laboratory {

    /**********************************************************
     * Constructors
     **********************************************************/

    /**
     * Initialize a new laboratory with a given capacity and a set of devices to be available to use.
     *
     * @param capacity
     *        The capacity of the storage inside this laboratory.
     * @param devices
     *        The list of devices which can be accessed in this laboratory.
     */
    public Laboratory(int capacity, ArrayList<Device> devices) {
        for (Device device : devices) {
            addDevice(device);
        }
        setCapacity(capacity);
    }


    /**********************************************************
     * Capacity
     **********************************************************/

    /**
     * Variable referencing the capacity of this laboratory expressed in storerooms.
     */
    private int capacity = 0;

    /**
     * Return the capacity of this laboratory.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Check if the given capacity is a valid capacity for a laboratory.
     *
     * @param   capacity
     *          The given capacity to be checked
     * @return  True if the given capacity is strictly positive, false otherwise.
     */
    protected static boolean isValidCapacity(int capacity) {
        if (capacity < 0) {
            return true;
        }
        return false;
    }

    /**
     * Set the capacity of this laboratory to the given capacity.
     *
     * @param   capacity
     *          The given capacity expressed in amount of storerooms.
     *
     * @effect  If the given capacity is a valid capacity, then the capacity of this
     *          laboratory is set to the given capacity.
     *          |  if (isValidCapacity(capacity))
     *          |    then this.capacity = capacity;
     */
    private void setCapacity(int capacity) {
        if (isValidCapacity(capacity)) {
            this.capacity = capacity;
        }
        throw new IllegalAmountException(capacity);
    }



    /**********************************************************
     * Devices
     **********************************************************/

    /**
     * Variable referencing the device(s) available to use in this laboratory.
     */
    private ArrayList<Device> devices = new ArrayList<Device>();

    /**
     * Return the device(s) available in this laboratory.
     */
    public ArrayList<Device> getDevices() {
        ArrayList<Device> copy = new ArrayList<>();
        for (Device device : devices) {
            copy.add(device);
        }
        return copy;
    }

    /**
     * Add a new device to this laboratory.
     *
     * @param   newDevice
     *          The new device to be added to this laboratory.
     *
     * @effect  If the new device is not a null-pointer, then the new device is
     *          added to this laboratory.
     *          | if(newDevice != null)
     *          |   then this.devices.add(newDevice)
     */
    protected void addDevice(Device newDevice) {
        if(newDevice != null)
            this.devices.add(newDevice);
    }



    /**********************************************************
     * Storage
     **********************************************************/

    /**
     * Variable referencing the alchemic ingredients stored in this laboratory.
     */
    private ArrayList<IngredientContainer> storage = new ArrayList<IngredientContainer>();

    /**
     * Add the contents of the given container to the laboratory.
     *
     * @param   container
     *          The given container to be added to storage.
     * @effect
     *
     * @effect  The given container is terminated.
     *          | container.terminate()
     */
    public void storeIngredient(IngredientContainer container) {
        UnitOfQuantity capacity = container.getCapacity();
        AlchemicIngredient ingredient = container.getIngredient();
        IngredientContainer newContainer = new IngredientContainer(capacity, ingredient);
        storage.add(newContainer);
        // Delete the old container
        container.terminate();
    }

    /**
     * Check if an ingredient with the given name is stored inside this laboratory.
     *
     * @param   name
     *          The given name of the alchemic ingredient to be checked
     * @return  True if, checking the name of each ingredient in storage, the given name matches the name of one of
     *          the ingredients in storage, false otherwise.
     *          | for each container in storage
     *          |   result == container.getIngredient().getName().equals(name)
     */
    public boolean isIngredientInStorage(String name) {
        for (IngredientContainer container : storage) {
            String ingName = container.getIngredient().getName();
            // Look for the given name
            if (ingName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the container from storage containing the ingredient with the given name.
     *
     * @pre     The given name must be the name of an ingredient which is indeed stored in this laboratory
     *          | isIngredientInStorage()
     * @param   name
     *          The given name of the ingredient to be found
     * @return  If the given name is a name for an ingredient which is stored in this laboratory, then
     *          the container containing the ingredient with that name is returned.
     *          | if isIngredientInStorage(name)
     *          |   for each container in storage
     *          |       if container.getIngredient().getName().equals(name)
     *          |           result = container
     * @throws NoSuchElementException
     *          | ! isIngredientInStorage()
     */
    public IngredientContainer getContainerIngredientWithName(String name) throws NoSuchElementException {
        if (isIngredientInStorage(name)) {
            for (IngredientContainer container : storage) {
                String ingName = container.getIngredient().getName();
                // Look for the given name
                if (ingName.equals(name)) {
                    return container;
                }
            }
        }
        throw new NoSuchElementException(name);
    }

    /**
     * Take a given amount, with an accompanying given unit, of a given ingredient from the storage of this laboratory.
     *
     * @pre     The given quantity must not be greater than the available quantity of the given ingredient in storage.
     *          | unit.getAmountSpoons() * amount <= ingUnit.getAmountSpoons() * ingAmount
     * @pre     The given quantity must be of the same state as the state of the available quantity of
     *          the given ingredient stored inside this laboratory.
     *          | unit.getState() == container.getIngredient().getQuantityUnit().getState()
     * @pre     The given ingredient must be stored in this laboratory.
     *          | isIngredientInStorage()
     * @param   name
     *          The given name of the ingredient to be taken
     * @param   amount
     *          The given amount of the ingredient
     * @param   unit
     *          The given unit of the amount
     * @effect  The quantity of the ingredient inside the container in storage is decreased by the given quantity.
     *          | ingredient.setQuantity((unit.getAmountSpoons() * amount - ingUnit.getAmountSpoons() * ingAmount),
     *          |       UnitOfQuantity.SPOON)
     * @post    The container containing the ingredient with the given name is terminated if the given quantity
     *          is the same as the available quantity of ingredient in storage.
     *          | if (unit.getAmountSpoons() * amount == ingUnit.getAmountSpoons() * ingAmount)
     *          |   then container.terminate()
     * @return  The given quantity of the ingredient with the given name in a new container.
     *          | result = new IngredientContainer(ingredient)
     * @throws  NoSuchElementException
     *          | ! isIngredientInStorage()
     * @throws  IllegalAmountException
     *          | unit.getAmountSpoons() * amount > ingUnit.getAmountSpoons() * ingAmount
     * @throws  IllegalArgumentException
     *          | unit.getState() != container.getIngredient().getQuantityUnit().getState()
     */
    public IngredientContainer takeIngredient(String name, int amount, UnitOfQuantity unit)
            throws NoSuchElementException, IllegalAmountException, IllegalArgumentException {
        if (isIngredientInStorage(name)) {
            IngredientContainer container = getContainerIngredientWithName(name);
            AlchemicIngredient ingredient = container.getIngredient();
            int ingAmount = ingredient.getQuantityAmount();
            UnitOfQuantity ingUnit = ingredient.getQuantityUnit();
            // Check if states are equal
            if (ingUnit.getState() == unit.getState()) {
                // Convert to spoons
                double ingSpoons = ingUnit.getAmountSpoons() * ingAmount;
                double spoons = unit.getAmountSpoons() * amount;
                // ingAmount - quantity
                if (spoons <= ingSpoons) {
                    double newQuantityAmount = (ingSpoons - spoons);
                    // Check if ingAmount is now null
                    if (ingAmount == 0) {
                        // Delete container from storage, since it is empty
                        container.terminate();
                    }
                    // Set the quantity of the taken ingredient to the new quantity
                    ingredient.setQuantity((int) newQuantityAmount, UnitOfQuantity.SPOON);
                    // Make the taken ingredient
                    AlchemicIngredient takenIngredient =
                            new AlchemicIngredient(ingredient.getIngredientType(), amount, unit);
                    IngredientContainer newContainer = new IngredientContainer(ingredient);
                    return newContainer;
                }
                // If asked amount is more than the quantity inside the laboratory:
                else {
                    throw new IllegalAmountException(amount);
                }
            }
            // States do not match
            else {
                throw new IllegalArgumentException("The given quantity is not of the same state.");
            }
        }
        throw new NoSuchElementException("No ingredient by that name is stored inside this Laboratory.");
    }

    /**
     * Take the whole ingredient with the given name from the storage of this laboratory.
     *
     * @pre     The given name must be the name of an ingredient stored inside this laboratory.
     *          | isIngredientInStorage(name)
     * @param   name
     *          The given name of the ingredient
     * @effect  If the name is a valid name, then the old container is terminated.
     *          | if isIngredientInStorage(name) then getContainerIngredientWithName(name).terminate()
     * @return  If the given name is a valid name, the ingredient with that name is placed in a new
     *          container with the best fitting capacity for that ingredient which is returned.
     *          | if isIngredientInStorage(name)
     *          |   then result = new IngredientContainer(ingredient)
     * @throws  NoSuchElementException
     *          | !isIngredientInStorage(name)
     */
    public IngredientContainer takeIngredient(String name) throws NoSuchElementException{
        if (isIngredientInStorage(name)) {
            IngredientContainer container = getContainerIngredientWithName(name);
            // Take out the ingredient
            AlchemicIngredient ingredient = container.getIngredient();
            // Terminate old container
            container.terminate();
            // Make new container with best fitting capacity
            IngredientContainer newContainer = new IngredientContainer(ingredient);
            return newContainer;
        }
        throw new NoSuchElementException("No ingredient by that name is stored inside this Laboratory.");
    }

    /**
     * Get the stored ingredients and their respective quantities.
     *
     * @return  For each container in storage, the name, the amount of quantity and the unit of quantity
     *          of the ingredient inside the container is returned.
     *          | for each container in storage
     *          |   result == "- " + ingName + ": " + ingQuantityAmount + " " + ingQuantityUnit + "\n"
     */
    public String getStoredIng() {
        String returnStr = new String();
        for (IngredientContainer container : storage) {
            String ingName = container.getIngredient().getName();
            int ingQuantityAmount = container.getIngredient().getQuantityAmount();
            UnitOfQuantity ingQuantityUnit = container.getIngredient().getQuantityUnit();

            returnStr += "- " + ingName + ": " + ingQuantityAmount + " " + ingQuantityUnit + "\n";
        }
        return returnStr;
    }


}
