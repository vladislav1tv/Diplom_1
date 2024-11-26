import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IngredientTypeTest {

    @Test
    public void testSauceType() {
        IngredientType type = IngredientType.SAUCE;
        assertNotNull("Тип SAUCE не null", type);
        assertEquals("Тип SAUCE", IngredientType.SAUCE, type);
    }

    @Test
    public void testFillingType() {
        IngredientType type = IngredientType.FILLING;
        assertNotNull("Тип FILLING не null", type);
        assertEquals("Тип должен быть FILLING", IngredientType.FILLING, type);
    }
}