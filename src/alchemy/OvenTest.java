package alchemy;

import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A JUnit (4) test class for testing the non-private methods of the Oven Class.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public class OvenTest {

    IngredientType honey;
    AlchemicIngredient lavenderHoney;
    IngredientContainer bottledHoney;
    IngredientType flower;
    AlchemicIngredient redFlower;
    IngredientContainer jarFlower;
    Oven oven, ovenIllegal, ovenCold;
    ArrayList<IngredientContainer> listContainerIllegal;


    @Before
    public void setUp() {
        this.honey = new IngredientType("Honey", State.LIQUID, new int[]{0, 17});
        this.lavenderHoney = new AlchemicIngredient(honey, 1, UnitOfQuantity.BOTTLE);
        this.bottledHoney = new IngredientContainer(lavenderHoney);
        ArrayList<IngredientContainer> listContainer = new ArrayList<IngredientContainer> (Arrays.asList(bottledHoney));
        this.oven = new Oven(listContainer, new int[] {0,40});
        this.ovenCold = new Oven(listContainer, new int[] {0,10});

        this.flower = new IngredientType("Flower", State.POWDER, new int[]{0, 18});
        this.redFlower = new AlchemicIngredient(flower, 1, UnitOfQuantity.SACHET);
        this.jarFlower = new IngredientContainer(redFlower);
        this.listContainerIllegal = new ArrayList<IngredientContainer> (Arrays.asList(bottledHoney, jarFlower));

    }

    @Test
    public void testConstructorIngredientType_Legal() {
        assertEquals(new ArrayList<AlchemicIngredient> (Arrays.asList(lavenderHoney)), this.oven.getContents());
        assertEquals(0, this.oven.getTemperature()[0]);
        assertEquals(40, this.oven.getTemperature()[1]);
    }

    @Test
    public void testConstructorIngredientType_IlLegal() {
        assertThrows(IllegalArgumentException.class, () -> this.ovenIllegal = new Oven(listContainerIllegal, new int[] {0,40}));
    }

    @Test
    public void testCanItBeHeated(){
        assertTrue(this.oven.canItBeHeated());
        assertFalse(this.ovenCold.canItBeHeated());
    }

    @Test
    public void testUse(){
        this.oven.use();
        assertEquals("Heated Honey", this.lavenderHoney.getName());
        assertEquals(this.lavenderHoney.getTemperature(), oven.getTemperature());
    }
}
