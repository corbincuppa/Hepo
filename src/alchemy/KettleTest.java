package alchemy;
import org.junit.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * A JUnit (4) test class for testing the non-private methods of the Kettle Class.
 *
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 * @version 1.0
 */
public class KettleTest {
    IngredientType flower, wallMold, floorMold;
    AlchemicIngredient oneIng, moreOfTheSame, wall, floor;
    IngredientContainer containerOneIng, containerMoreOfTheSame, containerWall, containerFloor;
    Kettle kettleOneIng, kettleFlower, kettleMix;


    @Before
    public void setUp() {
        this.flower = new IngredientType("Flower", State.LIQUID, new int[]{0, 11});
        this.wallMold = new IngredientType("Wall Mold", State.LIQUID, new int[]{0, 100});
        this.floorMold =  new IngredientType("Floor Mold", State.POWDER, new int[]{100, 0});

        this.oneIng = new AlchemicIngredient(flower, 10, UnitOfQuantity.SPOON);
        this.containerOneIng = new IngredientContainer(UnitOfQuantity.BARREL, oneIng);
        ArrayList<IngredientContainer> container = new ArrayList<>();
        container.add(containerOneIng);
        this.kettleOneIng = new Kettle(container);

        this.moreOfTheSame = new AlchemicIngredient(flower, 2, UnitOfQuantity.BOTTLE);
        this.containerMoreOfTheSame = new IngredientContainer(UnitOfQuantity.BARREL, moreOfTheSame);
        ArrayList<IngredientContainer> container2 = new ArrayList<>();
        container2.add(containerOneIng);
        container2.add(containerMoreOfTheSame);
        this.kettleFlower = new Kettle(container2);

        this.wall = new AlchemicIngredient(wallMold, 25, UnitOfQuantity.SPOON);
        this.floor = new AlchemicIngredient(floorMold, 75, UnitOfQuantity.SPOON);
        this.containerWall = new IngredientContainer(wall);
        this.containerFloor = new IngredientContainer(floor);
        ArrayList<IngredientContainer> containerMix = new ArrayList<>();
        containerMix.add(containerWall);
        containerMix.add(containerFloor);
        this.kettleMix = new Kettle(containerMix);

    }

    @Test
    public void testConstructorKettle(){
        ArrayList<AlchemicIngredient> content = new ArrayList<>();
        content.add(oneIng);
        Assert.assertEquals(content, kettleOneIng.getContents());
    }

    @Test
    public void testUseKettleOneIng(){
        Assert.assertThrows(IllegalArgumentException.class, () -> kettleOneIng.use());
    }

    @Test
    public void testUseKettleSameIng(){
        kettleFlower.use();
        AlchemicIngredient newIng = kettleFlower.takeResult().getIngredient();
        Assert.assertEquals(oneIng.getIngredientType(), newIng.getIngredientType());
        Assert.assertEquals(moreOfTheSame.getIngredientType(), newIng.getIngredientType());
        Assert.assertEquals(moreOfTheSame.getSimpleName(), newIng.getSimpleName());
        Assert.assertFalse(newIng.getIngredientType() instanceof IngredientTypeMixed);
        Assert.assertEquals(0, newIng.getTemperature()[0]);
        Assert.assertEquals(20, newIng.getTemperature()[1]);
        Assert.assertEquals(0, newIng.getIngredientType().getStdTemp()[0]);
        Assert.assertEquals(11, newIng.getIngredientType().getStdTemp()[1]);
        Assert.assertEquals(State.LIQUID, newIng.getState());
        Assert.assertEquals(State.LIQUID, newIng.getIngredientType().getStdState());
        Assert.assertEquals(32, newIng.getQuantityAmount());
        Assert.assertEquals(UnitOfQuantity.SPOON, newIng.getQuantityUnit());
    }

    @Test
    public void testUseKettleMix(){
        kettleMix.use();
        AlchemicIngredient newIng = kettleMix.takeResult().getIngredient();
        Assert.assertEquals("Wall Mold mixed with Floor Mold", newIng.getIngredientType().getName());
        Assert.assertFalse(wall.getIngredientType() == newIng.getIngredientType());
        Assert.assertFalse(floor.getIngredientType() == newIng.getIngredientType());
        Assert.assertTrue(newIng.getIngredientType() instanceof IngredientTypeMixed);
        Assert.assertEquals(50, newIng.getTemperature()[0]);
        Assert.assertEquals(0, newIng.getTemperature()[1]);
        Assert.assertEquals(0, newIng.getIngredientType().getStdTemp()[0]);
        Assert.assertEquals(100, newIng.getIngredientType().getStdTemp()[1]);
        Assert.assertEquals(State.LIQUID, newIng.getState());
        Assert.assertEquals(State.LIQUID, newIng.getIngredientType().getStdState());
        Assert.assertEquals(100, newIng.getQuantityAmount());
        Assert.assertEquals(UnitOfQuantity.SPOON, newIng.getQuantityUnit());
    }

}




