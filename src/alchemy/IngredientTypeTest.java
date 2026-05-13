package alchemy;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * A JUnit (4) test class for testing the non-private methods of the IngredientType Class.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public class IngredientTypeTest {


    private static IngredientType type;
    private static IngredientType name_null, name_uppercase, name_lowercase, name_symbol, name_mixed_with, name_short1, name_short2;
    private static IngredientType state_null, state_gas;


    @Before
    public void setUp() {
        type = new IngredientType("Type", State.LIQUID, new int[]{0, 20});
        name_null = new IngredientType(null, State.LIQUID, new int[]{0, 20});
        name_uppercase = new IngredientType("FLOWER", State.LIQUID, new int[]{0, 20});
        name_lowercase = new IngredientType("flower", State.LIQUID, new int[]{0, 20});
        name_symbol = new IngredientType("Flower: Rose", State.LIQUID, new int[]{0, 20});
        name_mixed_with = new IngredientType("Flower mixed with sugar", State.LIQUID, new int[]{0, 20});
        name_short1 = new IngredientType("Fl", State.LIQUID, new int[]{0, 20}); // 1 woord --> minstens 3 letter
        name_short2 = new IngredientType("The Flower That I Grew ", State.LIQUID, new int[]{0, 20}); // elke woord --> minstends 2 letters
    }
}
