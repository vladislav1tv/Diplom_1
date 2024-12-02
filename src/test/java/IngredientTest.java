import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class IngredientTest {

    public IngredientType type;
    public String name;
    public float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { IngredientType.FILLING, "cutlet", 100 },
                { IngredientType.SAUCE, "hot sauce", 150 },
                { IngredientType.SAUCE, "mayonnaise", 80 }
        });
    }

    // Тест для проверки создания ингредиента
    @Test
    public void testIngredientCreation() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertNotNull("Ингредиент не null", ingredient);
        assertEquals("Название ингредиента верное", name, ingredient.getName());
        assertEquals("Цена ингредиента верна", price, ingredient.getPrice(), 0);
        assertEquals("Тип ингредиента верный", type, ingredient.getType());
    }
}