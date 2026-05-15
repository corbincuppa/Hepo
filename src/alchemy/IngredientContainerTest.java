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
    AlchemicIngredient validIng, invalidQuant;
    IngredientContainer containerValidIng, containerInvalidQuant;


    @Before
    public void setUp() {
        this.flower = new IngredientType("Flower", State.LIQUID, new int[]{0, 18});
        this.validIng = new AlchemicIngredient(flower, 8, UnitOfQuantity.JUG);
        this.invalidQuant = new AlchemicIngredient(flower, 1, UnitOfQuantity.STOREROOM);
        this.containerValidIng = new IngredientContainer(UnitOfQuantity.BARREL, validIng);
        this.containerInvalidQuant = new IngredientContainer(UnitOfQuantity.BARREL, invalidQuant);
    }

    @Test
    public void testConstructorContainer_Legal(){
        Assert.assertEquals(this.validIng, containerValidIng.getIngredient());
        Assert.assertEquals(UnitOfQuantity.BARREL, containerValidIng.getCapacity());
    }

    @Test
    public void testConstructorContainer_IllegalQuant(){

    }

}

