package alchemy;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * A class of laboratories.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public class Laboratory {
    // CONSTRUCTORS IN BEGINNING OF CLASSES?
    /**********************************************************
     * Constructors
     **********************************************************/

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
     * Variable referencing the capacity of this laboratory expressed in spoons.
     */
    // NOT FINAL IN SPOONS
    private int capacity = 0;

    /**
     * Return the capacity of this laboratory.
     */
    public String getCapacity() {
        return this.capacity + " spoons.";
    }

    /**
     * Set the capacity of this laboratory to the given capacity.
     *
     * @param   capacity
     *          The given capacity
     *
     * @effect  If the given capacity is strictly positive, then the capacity of this
     *          laboratory is set to the given capacity.
     *          |  if (capacity < 0)
     *          |    then this.capacity = capacity;
     */
    private void setCapacity(int capacity) {
        if (capacity < 0)
            this.capacity += capacity;
    }



    /**********************************************************
     * Devices
     **********************************************************/

    /**
     * Variable referencing the device available to use in this laboratory.
     */
    private ArrayList<Device> devices = new ArrayList<Device>();

    /**
     * Return the devices available in this laboratory.
     */
    // returns in string so that devices list cannot be changed by the user.
    public String getDevices() {
        String returnStr = new String();
        for (Device device : devices) {
            returnStr += device;
        }
        System.out.println(returnStr);
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
    private void addDevice(Device newDevice) {
        if(newDevice != null)
            this.devices.add(newDevice);
    }



    /**********************************************************
     * Storage
     **********************************************************/

    /**
     * Variable referencing the alchemic ingredients stored in this laboratory.
     */
    private ArrayList<IngredientContainer> storage = new ArrayList<>();

    /**
     *
     * @param container
     */
    public void storeIngredient(IngredientContainer container) {
        //AlchemicIngredient ingredient = container.getIngredient();
        //Integer quantity = ingredient.getQuantity();
        //storage.put(ingredient, quantity);
        // THEN DELETE CONTAINER
        // this isn't right, storage should contain containers.
        storage.add(container);

    }

    /**
     *
     * @param name
     * @param quantity
     */
    public void takeIngredient(String name, int quantity) {

    }

    /**
     *
     * @param name
     */
    public void takeIngredient(String name) {

    }

    /**
     *
     * @return
     */
    public String getStoredIng() {
        String returnStr = new String();
    }


}
