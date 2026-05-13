package alchemy;

import java.util.ArrayList;
import java.util.Arrays;

public enum UnitOfQuantity {

    SPOON(1, State.BOTH, "spoon"),

    DROP(0.125, State.LIQUID, "drop"),  // 8 drops = 1 spoon
    // spoon
    VIAL(5, State.LIQUID, "vial"),      // 1 vial = 5 spoons
    BOTTLE(15, State.LIQUID, "bottle"),     // 1 bottle = 3 vials
    JUG(105, State.LIQUID, "jug"),      // 1 jug = 7 bottles
    BARREL(1260, State.LIQUID, "barrel"),     // 1 barrel = 12 jugs

    PINCH( 1/6, State.POWDER, "pinch"),
    // spoon
    SACHET(7, State.POWDER, "sachet"),
    BOX(42, State.POWDER, "box"),
    SACK(126, State.POWDER, "sack"),
    CHEST(1260, State.POWDER, "chest"),


    STOREROOM(6300, State.BOTH, "storeroom");

    private final double amountSpoons;
    private final State state;
    private final String unit;

    UnitOfQuantity(double amountSpoons, State state, String unit) {
        this.amountSpoons = amountSpoons;
        this.state = state;
        this.unit = unit;
    }

    protected String getUnit() {
        return unit;
    }

    protected State getState() {
        return state;
    }

    protected double getAmountSpoons() {
        return amountSpoons;
    }

    public ArrayList<UnitOfQuantity> getInOrder(State state){
        if (state == State.LIQUID) {
            return new ArrayList<UnitOfQuantity>(Arrays.asList(UnitOfQuantity.SACHET,UnitOfQuantity.BOX,UnitOfQuantity.SACK,UnitOfQuantity.CHEST));
        }

        if (state == State.POWDER) {
            return new ArrayList<UnitOfQuantity>(Arrays.asList(UnitOfQuantity.VIAL, UnitOfQuantity.BOTTLE, UnitOfQuantity.JUG, UnitOfQuantity.BARREL));
        }
    }

    public UnitOfQuantity getBestUnit(int amountSpoons, State state){
        ArrayList<UnitOfQuantity> order = getInOrder(state);
        for (UnitOfQuantity unit : order) {

            double unitSpoons = unit.getAmountSpoons();

            if (amountSpoons <= unitSpoons) {
                capacity = unit;
            }
        }
    }

}

