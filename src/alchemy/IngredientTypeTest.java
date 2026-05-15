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


    IngredientType flower, flowerIllegal;


    @Before
    public void setUp() {
        this.flower = new IngredientType("Flower", State.LIQUID, new int[]{0, 18});
        this.flowerIllegal = new IngredientType("flowers I LikE!", null, new int[]{18, 18});
    }

    @Test
    public void testConstructorIngredientType_Legal() {
        assertEquals("Flower", this.flower.getName());
        assertSame(State.LIQUID, this.flower.getStdState());
        assertEquals(new int[]{0, 18}, this.flower.getStdTemp());
    }

    @Test
    public void testConstructorIngredientType_Illegal() {
        assertEquals("Ingredient Type", this.flowerIllegal.getName());
        //state???
        assertEquals(new int[]{0, 20}, this.flowerIllegal.getStdTemp());
    }

    @Test
    public void testCanHaveAsName_allCases() {
        assertFalse(flower.canHaveAsName(null));
        assertFalse(flower.canHaveAsName("FLOWER"));
        assertFalse(flower.canHaveAsName("flower"));
        assertFalse(flower.canHaveAsName("Flower: Rose"));
        assertFalse(flower.canHaveAsName("Flower mixed with Sugar"));
        assertFalse(flower.canHaveAsName("Fl"));
        assertFalse(flower.canHaveAsName("The Flower That I Grew"));
        assertTrue(flower.canHaveAsName("Little Red Flowers"));
    }

    @Test
    public void testisValidState_allCases() {
        assertFalse(flower.isValidStdState(null));
        for (State s: State.values()) {
            assertTrue(flower.isValidStdState(s));
        }
    }

    @Test
    public void testCanHaveAsStdTemperature_allCases() {
        assertFalse(flower.canHaveAsStdTemperature(new int[]{0, 0}, 10000));
        assertFalse(flower.canHaveAsStdTemperature(new int[]{20, 0}, 10000));
        assertFalse(flower.canHaveAsStdTemperature(new int[]{0, -20}, 10000));
        assertFalse(flower.canHaveAsStdTemperature(new int[]{20, 20}, 10000));
        assertTrue(flower.canHaveAsStdTemperature(new int[]{0, 5}, 10000));
    }
}
