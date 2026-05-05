package alchemy;

public enum UnitOfQuantity {
    DROP(0.125, SPOON),  // 8 drops = 1 spoon
    SPOON(1, null),
    VIAL(5, SPOON),      // 1 vial = 5 spoons
    BOTTLE(3, VIAL),     // 1 bottle = 3 vials
    JUG(7, BOTTLE),      // 1 jug = 7 bottles
    BARREL(12, JUG),     // 1 barrel = 12 jugs
    STOREROOML(5, BARREL), // 1 storeroom = 5 barrels

    PINCH(0.33, SPOON),
    // spoon
    SACHET(7, SPOON),
    BOX(6, SACHET),
    SACK(3, BOX),
    CHEST(10, SACK),
    STOREROOMP(5, CHEST);

    private final double conversionFactor;
    private final UnitOfQuantity smallerUnit;

    UnitOfQuantity(int conversionFactor, UnitOfQuantity smallerUnit) {
        this.conversionFactor = conversionFactor;
        this.smallerUnit = smallerUnit;
    }

    //getQuantity in spoons?

    }
}
