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



    @Before
    public void setUp() {
        this.flower = new IngredientType("Flower", State.LIQUID, new int[]{0, 18});
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

