package alchemy;

import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A JUnit (4) test class for testing the non-private methods of the CoolingBox Class.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */

public class CoolingBoxTest {
    IngredientType honey;
    AlchemicIngredient lavenderHoney;
    IngredientContainer bottledHoney;
    IngredientType flower;
    AlchemicIngredient redFlower;
    IngredientContainer jarFlower;
    CoolingBox coolingBox, coolingBoxIllegal, coolingBoxWarm;
    ArrayList<IngredientContainer> listContainerIllegal;


    @Before
    public void setUp() {
        this.honey = new IngredientType("Honey", State.LIQUID, new int[]{0, 17});
        this.lavenderHoney = new AlchemicIngredient(honey, 1, UnitOfQuantity.BOTTLE);
        this.bottledHoney = new IngredientContainer(lavenderHoney);
        ArrayList<IngredientContainer> listContainer = new ArrayList<IngredientContainer> (Arrays.asList(bottledHoney));
        this.coolingBox = new CoolingBox(listContainer, new int[] {0,10});
        this.coolingBoxWarm = new CoolingBox(listContainer, new int[] {0,40});

        this.flower = new IngredientType("Flower", State.POWDER, new int[]{0, 18});
        this.redFlower = new AlchemicIngredient(flower, 1, UnitOfQuantity.SACHET);
        this.jarFlower = new IngredientContainer(redFlower);
        this.listContainerIllegal = new ArrayList<IngredientContainer> (Arrays.asList(bottledHoney, jarFlower));
    }

    @Test
    public void testConstructorIngredientType_Legal() {
        assertEquals(new ArrayList<AlchemicIngredient> (Arrays.asList(lavenderHoney)), this.coolingBox.getContents());
        assertEquals(new int[]{0, 10}, this.coolingBox.getTemperature());
    }

    @Test
    public void testConstructorIngredientType_IlLegal() {
        assertThrows(IllegalArgumentException.class, () -> this.coolingBoxIllegal = new CoolingBox(listContainerIllegal, new int[] {0,10}));
    }

    @Test
    public void testCanItBeCooled(){
        assertTrue(this.coolingBox.canItBeCooled());
        assertFalse(this.coolingBoxWarm.canItBeCooled());
    }

    @Test
    public void testUse(){
        this.coolingBox.use();
        assertEquals("Cooled Honey", this.lavenderHoney.getName());
        assertEquals(new int[] {0,10}, lavenderHoney.getTemperature());
    }
}
