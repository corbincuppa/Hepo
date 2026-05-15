package alchemy;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * A JUnit (4) test class for testing the non-private methods of the IngredientContainer Class.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */
public class IngredientContainerTest {
    IngredientType flower;
    AlchemicIngredient validIng, fullIng, invalidQuant;
    IngredientContainer containerValidIng, containerFull, containerInvalidQuant;


    @Before
    public void setUp() {
        this.flower = new IngredientType("Flower", State.LIQUID, new int[]{0, 18});
        this.validIng = new AlchemicIngredient(flower, 8, UnitOfQuantity.JUG);
        this.fullIng= new AlchemicIngredient(flower, 1, UnitOfQuantity.BARREL);
        this.containerValidIng = new IngredientContainer(UnitOfQuantity.BARREL, validIng);
        this.containerFull = new IngredientContainer(UnitOfQuantity.BARREL, fullIng);
        this.invalidQuant = new AlchemicIngredient(flower, 1, UnitOfQuantity.STOREROOM);
        this.containerInvalidQuant = new IngredientContainer(UnitOfQuantity.SPOON, invalidQuant);
    }

    @Test
    public void testConstructorContainer_Legal(){
        Assert.assertEquals(this.validIng.getName(), containerValidIng.getIngredient().getName());
        Assert.assertEquals(UnitOfQuantity.BARREL, containerValidIng.getCapacity());
        Assert.assertEquals(this.fullIng.getName(), containerValidIng.getIngredient().getName());
        Assert.assertEquals(UnitOfQuantity.BARREL, containerFull.getCapacity());
    }

    @Test
    public void testInvalidCap(){
        Assert.assertFalse(IngredientContainer.isValidCapacity(UnitOfQuantity.DROP));
        Assert.assertFalse(IngredientContainer.isValidCapacity(UnitOfQuantity.PINCH));
        Assert.assertFalse(IngredientContainer.isValidCapacity(UnitOfQuantity.STOREROOM));
    }

    @Test
    public void testInvalidQuant(){
        int amount = invalidQuant.getQuantityAmount();
        UnitOfQuantity unit = invalidQuant.getQuantityUnit();
        assertFalse(containerInvalidQuant.canHaveAsQuantity(amount, unit));
    }

    @Test
    public void testGetIngredient(){
        assertEquals(validIng, containerValidIng.getIngredient());
    }

    @Test
    public void testGetCapacity(){
        assertEquals(UnitOfQuantity.BARREL, containerValidIng.getCapacity());
    }

}

