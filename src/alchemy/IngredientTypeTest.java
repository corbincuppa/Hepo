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


    IngredientType flower;
    IngredientType name_null, name_uppercase, name_lowercase, name_symbol, name_mixed_with, name_short1, name_short2;
    IngredientType state_null;
    IngredientType temp_zero, temp_cold, temp_neg, temp_warm_cold;


    @Before
    public void setUp() {
        this.flower = new IngredientType("Flower", State.LIQUID, new int[]{0, 18});

        this.name_null = new IngredientType(null, State.LIQUID, new int[]{0, 20});
        this.name_uppercase = new IngredientType("FLOWER", State.LIQUID, new int[]{0, 20});
        this.name_lowercase = new IngredientType("flower", State.LIQUID, new int[]{0, 20});
        this.name_symbol = new IngredientType("Flower: Rose", State.LIQUID, new int[]{0, 20});
        this.name_mixed_with = new IngredientType("Flower mixed with sugar", State.LIQUID, new int[]{0, 20});
        this.name_short1 = new IngredientType("Fl", State.LIQUID, new int[]{0, 20}); // 1 woord --> minstens 3 letter
        this.name_short2 = new IngredientType("The Flower That I Grew ", State.LIQUID, new int[]{0, 20}); // elke woord --> minstends 2 letters

        this.state_null = new IngredientType("Flower", null, new int[]{0, 20});

        this.temp_zero = new IngredientType("Flower", State.LIQUID, new int[]{0, 0});
        this.temp_cold = new IngredientType("Flower", State.LIQUID, new int[]{20, 0});
        this.temp_neg = new IngredientType("Flower", State.LIQUID, new int[]{0, -20});
        this.temp_warm_cold = new IngredientType("Flower", State.LIQUID, new int[]{20, 20});
    }

    @Test
    public void testConstructorIngredientType_Legal(){
        Assert.assertEquals("Flower", this.flower.getName());
        Assert.assertEquals(State.LIQUID,this.flower.getStdState());
        Assert.assertEquals(new int[]{0, 18}, this.flower.getStdTemp());
    }

    @Test
    public void testConstructorIngredientType_Illegal_Name(){
        Assert.assertEquals("ingredient_type",this.name_null.getName());
        Assert.assertEquals("ingredient_type",this.name_uppercase.getName());
        Assert.assertEquals("ingredient_type",this.name_lowercase.getName());
        Assert.assertEquals("ingredient_type",this.name_symbol.getName());
        Assert.assertEquals("ingredient_type",this.name_mixed_with.getName());
        Assert.assertEquals("ingredient_type",this.name_short1.getName());
        Assert.assertEquals("ingredient_type",this.name_short2.getName());
    }

    @Test
    public void testConstructorIngredientType_Illegal_State(){
        assertNull(this.state_null.getStdState()); // is niet de bedoeling
    }

    @Test
    public void testConstructorIngredientType_Illegal_Temp(){
        Assert.assertEquals(new int[]{0, 20},this.temp_zero.getStdTemp());
        Assert.assertEquals(new int[]{0, 20},this.temp_cold.getStdTemp());
        Assert.assertEquals(new int[]{0, 20},this.temp_neg.getStdTemp());
        Assert.assertEquals(new int[]{0, 20},this.temp_warm_cold.getStdTemp());
    }
}
