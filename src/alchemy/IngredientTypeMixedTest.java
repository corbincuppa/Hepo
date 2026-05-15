package alchemy;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * A JUnit (4) test class for testing the non-private methods of the IngredientTypeMixed Class.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public class IngredientTypeMixedTest {
    IngredientType flower, flowerIllegal;


    @Before
    public void setUp() {
        this.flower = new IngredientTypeMixed("Flower mixed with Sugar", State.LIQUID, new int[]{0, 18});
        this.flowerIllegal = new IngredientTypeMixed("flowers mixed With Sugar", null, new int[]{18, 18});
    }

    @Test
    public void testConstructorIngredientType_Legal() {
        assertEquals("Flower mixed with Sugar", this.flower.getName());
        assertSame(State.LIQUID, this.flower.getStdState());
        assertEquals(0, this.flower.getStdTemp()[0]);
        assertEquals(18, this.flower.getStdTemp()[1]);
    }

    @Test
    public void testConstructorIngredientType_Illegal() {
        assertEquals("Ingredient Type Mixed", this.flowerIllegal.getName());
        //state???
        assertEquals(0, this.flowerIllegal.getStdTemp()[0]);
        assertEquals(20, this.flowerIllegal.getStdTemp()[1]);
    }

    @Test
    public void testCanHaveAsName_allCases() {
        assertFalse(flower.canHaveAsName(null));
        assertFalse(flower.canHaveAsName("FLOWER"));
        assertFalse(flower.canHaveAsName("flower"));
        assertFalse(flower.canHaveAsName("Flower: Rose"));
        assertFalse(flower.canHaveAsName("Fl"));
        assertFalse(flower.canHaveAsName("The Flower That I Grew"));

        assertTrue(flower.canHaveAsName("Little Red Flowers"));
        assertTrue(flower.canHaveAsName("Flower mixed with Sugar"));

        assertFalse(flower.canHaveAsName("Flower Mixed With Sugar"));
        assertFalse(flower.canHaveAsName("Flower MIXED WITH Sugar"));
        assertFalse(flower.canHaveAsName("Flower miXEd wITh Sugar"));
    }
}
