package alchemy;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.round;

/**
 * An enum of units of quantity.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */
public enum UnitOfQuantity {

    SPOON(1, State.BOTH, "spoon"),

    DROP(0.125, State.LIQUID, "drop"),  // 8 drops = 1 spoon
    VIAL(5, State.LIQUID, "vial"),      // 1 vial = 5 spoons
    BOTTLE(15, State.LIQUID, "bottle"),     // 1 bottle = 3 vials
    JUG(105, State.LIQUID, "jug"),      // 1 jug = 7 bottles
    BARREL(1260, State.LIQUID, "barrel"),     // 1 barrel = 12 jugs

    PINCH( 0.33, State.POWDER, "pinch"),
    SACHET(7, State.POWDER, "sachet"),
    BOX(42, State.POWDER, "box"),
    SACK(126, State.POWDER, "sack"),
    CHEST(1260, State.POWDER, "chest"),


    STOREROOM(6300, State.BOTH, "storeroom");

    /**
     * Variable referencing the unit in amount of spoons
     */
    private final double amountSpoons;

    /**
     * Variable referencing the state in which the unit is applicable.
     */
    private final State state;

    /**
     * Variable referencing the unit expressed as a String.
     */
    private final String unit;

    /**
     * Initialize a new unit of quantity with the unit expressed in amount of spoons and a given state.
     *
     * @param   amountSpoons
     *          This unit expressed in amount of spoons
     * @param   state
     *          The given state for which this unit is valid
     * @param unit
     */
    UnitOfQuantity(double amountSpoons, State state, String unit) {
        this.amountSpoons = amountSpoons;
        this.state = state;
        this.unit = unit;
    }

    /**
     * Return the string of this unit.
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Return the state in which this unit is valid.
     */
    public State getState() {
        return state;
    }

    /**
     * Return this unit in amount of spoons.
     */
    public double getAmountSpoons() {
        return amountSpoons;
    }

    /**
     * Return, given a state, the units in ascending order based on the amount of spoons, excluding
     * the smallest and biggest units.
     *
     * @param   state
     *          The given state
     * @throws  IllegalArgumentException
     *          | state != State.POWDER || state != State.LIQUID
     */
    public static ArrayList<UnitOfQuantity> getInOrder(State state) throws IllegalArgumentException{
        if (state == State.POWDER) {
            return new ArrayList<>(Arrays.asList(UnitOfQuantity.SPOON, UnitOfQuantity.SACHET,UnitOfQuantity.BOX,UnitOfQuantity.SACK,UnitOfQuantity.CHEST));
        }

        if (state == State.LIQUID) {
            return new ArrayList<>(Arrays.asList(UnitOfQuantity.SPOON, UnitOfQuantity.VIAL, UnitOfQuantity.BOTTLE, UnitOfQuantity.JUG, UnitOfQuantity.BARREL));
        }
        else throw new IllegalArgumentException("Such state is not supported: "+state);
    }

    /**
     * Return the loosely fitting unit for the given amount of spoons and given state.
     *
     * @param   amountSpoons
     *          The given amount of spoons
     * @param   state
     *          The given state in which the given amount of spoons is valid
     */
    public static UnitOfQuantity getBestUnit(double amountSpoons, State state){
        ArrayList<UnitOfQuantity> order = getInOrder(state);
        for (UnitOfQuantity unit : order) {
            double unitSpoons = unit.getAmountSpoons();
            if (amountSpoons <= unitSpoons) {
                return unit;
            }
        }
        return order.get(order.size() - 1);
    }

    /**
     * Return the best fitting amount of unit for the given amount of spoons and given state.
     *
     * @param   amountSpoons
     *          The given amount of spoons
     * @param   state
     *          The given state in which the given amount of spoons is valid
     */
    public static int getBestFitAmount(double amountSpoons, State state) {
        ArrayList<UnitOfQuantity> order = getInOrder(state);
        // Default to the largest unit if amount exceeds all units
        UnitOfQuantity bestUnit = getBestFitUnit(amountSpoons, state);
        double amount = amountSpoons / bestUnit.getAmountSpoons();
        return (int)amount;
    }

    /**
     * Return the best fitting unit for the given amount of spoons and given state.
     *
     * @param   amountSpoons
     *          The given amount of spoons
     * @param   state
     *          The given state in which the given amount of spoons is valid
     */
    public static UnitOfQuantity getBestFitUnit(double amountSpoons, State state) {
        ArrayList<UnitOfQuantity> order = getInOrder(state);
        // Default to the largest unit if amount exceeds all units
        UnitOfQuantity bestUnit = order.getLast();
        for (int i = 0; i < order.size() - 1; i++) {
            UnitOfQuantity next = order.get(i + 1);
            if (amountSpoons < next.getAmountSpoons()) {
                bestUnit = order.get(i);
                return bestUnit;
            }
        }
        return order.get(order.size() - 1);
    }

}

