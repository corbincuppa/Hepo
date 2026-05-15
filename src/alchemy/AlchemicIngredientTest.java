package alchemy;

import static org.junit.Assert.*;

import exceptions.IllegalNameException;
import org.junit.*;

/**
 * A JUnit (4) test class for testing the non-private methods of the IngredientTypeMixed Class.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public class AlchemicIngredientTest {
    IngredientType flower,flowerMixed;
    AlchemicIngredient redFlower, redFlowerMixed;

    @Before
    public void setUp() {
        this.flower = new IngredientType("Flower", State.POWDER, new int[]{0, 18});
        this.flowerMixed = new IngredientType("Flower mixed with Sugar", State.POWDER, new int[]{0, 18});
        this.redFlower = new AlchemicIngredient(flower, 5, UnitOfQuantity.SPOON);
        this.redFlowerMixed = new AlchemicIngredient(flowerMixed, 5, UnitOfQuantity.SPOON);
    }

    @Test
    public void testConstructorAlchemicIngredient_Legal() {
        assertEquals("Flower", this.redFlower.getName());
        assertSame(flower, this.redFlower.getIngredientType());
        assertSame(State.POWDER, this.redFlower.getState());
        assertEquals(5, this.redFlower.getQuantityAmount());
        assertSame(UnitOfQuantity.SPOON, this.redFlower.getQuantityUnit());
        assertEquals(new int[]{0, 18}, this.redFlower.getTemperature());

        assertEquals("Flower mixed with Sugar", this.redFlowerMixed.getName());
        assertSame(flowerMixed, this.redFlowerMixed.getIngredientType());
        assertSame(State.POWDER, this.redFlowerMixed.getState());
        assertEquals(5, this.redFlowerMixed.getQuantityAmount());
        assertSame(UnitOfQuantity.SPOON, this.redFlowerMixed.getQuantityUnit());
        assertEquals(new int[]{0, 18}, this.redFlowerMixed.getTemperature());
    }

    @Test
    public void testGetterName(){
        assertEquals("Flower", this.redFlower.getSimpleName());
        assertEquals("Flower", this.redFlower.getFullName());
        assertNull(this.redFlower.getSpecialName());
        assertEquals("Flower", this.redFlower.getName());
    }

    @Test
    public void testChangeFullName(){
        redFlower.changeFullName("Red Flower");

        assertEquals("Flower", this.redFlower.getSimpleName());
        assertEquals("Red Flower", this.redFlower.getFullName());
        assertNull(this.redFlower.getSpecialName());
        assertEquals("Red Flower", this.redFlower.getName());
    }

    @Test
    public void testSetSpecialName(){
        assertThrows(IllegalArgumentException.class,
                () -> redFlower.setSpecialName("Rose"));
    }

    @Test
    public void testGetterNameMixed(){
        assertEquals("Flower mixed with Sugar", this.redFlowerMixed.getSimpleName());
        assertEquals("Flower mixed with Sugar", this.redFlowerMixed.getFullName());
        assertNull(this.redFlowerMixed.getSpecialName());
        assertEquals("Flower mixed with Sugar", this.redFlowerMixed.getName());
    }

    @Test
    public void testChangeNameMixed(){
        redFlowerMixed.changeFullName("Red Flower mixed with Sugar");
        assertEquals("Flower mixed with Sugar", this.redFlowerMixed.getSimpleName());
        assertEquals("Red Flower mixed with Sugar", this.redFlowerMixed.getFullName());
        assertNull(this.redFlowerMixed.getSpecialName());
        assertEquals("Red Flower mixed with Sugar", this.redFlowerMixed.getName());
    }

    @Test
    public void testSetSpecialNameMixed(){
        redFlowerMixed.changeFullName("Red Flower mixed with Sugar");
        try {
            redFlowerMixed.setSpecialName("Rose Syrup");
        } catch (IllegalNameException e) {
            throw new RuntimeException(e);
        }
        assertEquals("Flower mixed with Sugar", this.redFlowerMixed.getSimpleName());
        assertEquals("Red Flower mixed with Sugar", this.redFlowerMixed.getFullName());
        assertEquals("Rose", this.redFlowerMixed.getSpecialName());
        assertEquals("Rose (Red Flower mixed with Sugar)", this.redFlowerMixed.getName());
    }

    @Test
    public void testIsValidSpecialName(){
        assertFalse(redFlowerMixed.isSpecialNameValid(null));
        assertFalse(redFlowerMixed.isSpecialNameValid("ROSE SYRUP"));
        assertFalse(redFlowerMixed.isSpecialNameValid("rose syrup"));
        assertFalse(redFlowerMixed.isSpecialNameValid("Rose Syrup !!!"));
        assertFalse(redFlowerMixed.isSpecialNameValid("Rose Syrup mixed with Lemon"));
        assertFalse(redFlowerMixed.isSpecialNameValid("Rs"));
        assertFalse(redFlowerMixed.isSpecialNameValid("The Rose Syrup That I Made"));
        assertTrue(redFlowerMixed.isSpecialNameValid("Fresh Rose Syrup"));
        assertTrue(redFlowerMixed.isSpecialNameValid("Rose Syrup (Homemade)"));
    }

    @Test
    public void TestCanHaveAsTemperature(){
        assertFalse(redFlower.canHaveAsTemperature(new int[]{0, -20}, 10000));
        assertFalse(redFlower.canHaveAsTemperature(new int[]{20, 20}, 10000));
        assertFalse(redFlower.canHaveAsTemperature(new int[]{-20, -20}, 10000));
        assertFalse(redFlower.canHaveAsTemperature(new int[]{-20, 0}, 10000));

        assertTrue(redFlower.canHaveAsTemperature(new int[]{0, 0}, 10000));
        assertTrue(redFlower.canHaveAsTemperature(new int[]{5, 0}, 10000));
        assertTrue(redFlower.canHaveAsTemperature(new int[]{0, 5}, 10000));
    }
}
