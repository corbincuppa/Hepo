package alchemy;

import java.util.ArrayList;

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
     * Add the contents of the given container to the laboratory.
     *
     * @param  container
     *         The given container to be added to storage.
     *
     * @effect  OLD CONTAINER IS DESTROYED
     */
    public void storeIngredient(IngredientContainer container) {
        //AlchemicIngredient ingredient = container.getIngredient();
        //Integer quantity = ingredient.getQuantity();
        //storage.put(ingredient, quantity);
        // this isn't right, storage should contain containers.

        int capacity = container.getCapacity();
        AlchemicIngredient ingredient = container.getIngredient();
        IngredientContainer newContainer = new IngredientContainer(capacity, ingredient);
        storage.add(newContainer);
        // THEN DELETE CONTAINER

    }

    /**
     *
     *
     * @param name
     * @param quantity
     */
    public IngredientContainer takeIngredient(String name, int quantity) {
        for (IngredientContainer container : storage) {
            String ingName = container.getIngredient().getName();
            int ingQuantity = container.getIngredient().getQuantity();
            if (ingName.equals(name)) {
                // ingQuantity - quantity
                container.getIngredient().setQuantity(-quantity);
                // check if ingQuantity is now null
                if (ingQuantity == 0) {
                    // delete container from storage
                }
                // new container with taken ingredient
                IngredientContainer newContainer = new IngredientContainer(quantity, container.getIngredient());
                return newContainer;
            }

            if (!ingName.equals(name)) {
                // EXCEPTION INGREDIENT NOT IN STORAGE
            }
        }
    }

    /**
     *
     * @param name
     */
    public IngredientContainer takeIngredient(String name) {
        for (IngredientContainer container : storage) {
            String ingName = container.getIngredient().getName();
            int ingQuantity = container.getIngredient().getQuantity();
            if (ingName.equals(name)) {

                // delete container from storage

                // new container with taken ingredient
                IngredientContainer newContainer = new IngredientContainer(ingQuantity, container.getIngredient());
                return newContainer;
            }

            if (!ingName.equals(name)) {
                // EXCEPTION INGREDIENT NOT IN STORAGE
            }
        }
    }

    /**
     * Returns the stored ingredients and their respective quantities.
     */
    //    CHECK QUANTITIES, HOW EXPRESSED?!?!?!!?
    public String getStoredIng() {
        String returnStr = new String();
        // THERE CAN ALSO BE NO CONTAINERS
        for (IngredientContainer container : storage) {
            String ingName = container.getIngredient().getName();
            int ingQuantity = container.getIngredient().getQuantity();

            returnStr += "- " + ingName + ", " + ingQuantity + "\n";
        }
        System.out.println(returnStr);
    }


}
