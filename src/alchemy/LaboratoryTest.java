package alchemy;
import org.junit.*;
import java.util.ArrayList;

/**
 * A JUnit (4) test class for testing the non-private methods of the Laboratory Class.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */
public class LaboratoryTest {
    IngredientType flower;
    AlchemicIngredient validIng;
    IngredientContainer containerValidIng;
    Laboratory lab;
    Oven oven;
    CoolingBox cooler, cooler2electricboogaloo;


    @Before
    public void setUp() {
        this.flower = new IngredientType("Flower", State.LIQUID, new int[]{0, 18});
        this.validIng = new AlchemicIngredient(flower, 8, UnitOfQuantity.JUG);
        this.containerValidIng = new IngredientContainer(UnitOfQuantity.BARREL, validIng);
        this.oven = new Oven(containerValidIng, new int[]{20, 0});
        this.cooler = new CoolingBox(containerValidIng, new int[]{20, 0});
        ArrayList<Device> devices = new ArrayList<>();
        devices.add(oven);
        devices.add(cooler);
        this.lab = new Laboratory(1, devices);
        this.cooler2electricboogaloo = new CoolingBox(containerValidIng, new int[]{90, 0});
    }

    @Test
    public void testConstructorLaboratory(){
        Assert.assertEquals(1, lab.getCapacity());
        ArrayList<Device> devices = new ArrayList<>();
        devices.add(oven);
        devices.add(cooler);
        Assert.assertEquals(devices, lab.getDevices());
        Assert.assertNull(lab.getStoredIng());
    }

    @Test
    public void testStoreIngredient(){
        lab.storeIngredient(containerValidIng);
        Assert.assertEquals("- "+validIng+": "+validIng.getQuantityAmount()+" "+validIng.getQuantityUnit()+"\n",
                lab.getStoredIng());
    }

    @Test
    public void testIsValidCap(){
        Assert.assertFalse(Laboratory.isValidCapacity(-1));
        Assert.assertFalse(Laboratory.isValidCapacity(0));
        Assert.assertTrue(Laboratory.isValidCapacity(1));
    }

    @Test
    public void testAddDevice(){
        ArrayList<Device> devices = new ArrayList<>();
        devices.add(oven);
        devices.add(cooler);
        Assert.assertEquals(devices, lab.getDevices());
        Assert.assertEquals(devices, lab.getDevices());
        lab.addDevice(cooler2electricboogaloo);
        devices.add(cooler2electricboogaloo)
        Assert.assertEquals(devices, lab.getDevices());
    }

    @Test
    public void testIsIngInStorage(){
        lab.storeIngredient(containerValidIng);
        Assert.assertTrue(lab.isIngredientInStorage("Flower"));
    }

    @Test
    public void testGetContIngWithName(){
        lab.storeIngredient(containerValidIng);
        Assert.assertEquals(this.containerValidIng, lab.getContainerIngredientWithName("Flower"));
    }


}




