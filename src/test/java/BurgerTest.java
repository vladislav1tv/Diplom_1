import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class BurgerTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockSauce;

    @Mock
    private Ingredient mockFilling;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Тест для проверки добавления ингредиента в бургер
    @Test
    public void testAddIngredient() {
        Burger burger = new Burger();
        when(mockSauce.getName()).thenReturn("ketchup");
        when(mockSauce.getPrice()).thenReturn(50f);
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);

        burger.addIngredient(mockSauce);
        List<Ingredient> ingredients = burger.ingredients;

        assertTrue("Список ингредиентов должен содержать добавленный ингредиент", ingredients.contains(mockSauce));
    }

    // Тест для проверки удаления ингредиента из бургера
    @Test
    public void testRemoveIngredient() {
        Burger burger = new Burger();
        when(mockSauce.getName()).thenReturn("sausage");
        when(mockSauce.getPrice()).thenReturn(300f);
        when(mockSauce.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(mockSauce);
        burger.removeIngredient(0);
        List<Ingredient> ingredients = burger.ingredients;

        assertTrue("Список ингредиентов должен быть пустым", ingredients.isEmpty());
    }

    // Тест для проверки перемещения ингредиента в бургере
    @Test
    public void testMoveIngredient() {
        Burger burger = new Burger();
        when(mockSauce.getName()).thenReturn("sour cream");
        when(mockSauce.getPrice()).thenReturn(200f);
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);

        when(mockFilling.getName()).thenReturn("cutlet");
        when(mockFilling.getPrice()).thenReturn(100f);
        when(mockFilling.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        burger.moveIngredient(0, 1);
        List<Ingredient> ingredients = burger.ingredients;

        assertEquals("Первый ингредиент должен быть котлета", mockFilling, ingredients.get(0));
        assertEquals("Второй ингредиент должен быть сметана", mockSauce, ingredients.get(1));
    }

    // Тест для проверки расчета цены бургера
    @Test
    public void testGetPrice() {
        Burger burger = new Burger();
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(100f);

        when(mockSauce.getName()).thenReturn("hot sauce");
        when(mockSauce.getPrice()).thenReturn(100f);
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);

        when(mockFilling.getName()).thenReturn("dinosaur");
        when(mockFilling.getPrice()).thenReturn(200f);
        when(mockFilling.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        assertEquals("Цена бургера должна быть 500", 500, burger.getPrice(), 0);
    }

    // Тест для проверки создания чека бургера
    @Test
    public void testGetReceipt() {
        Burger burger = new Burger();
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(150f);

        when(mockSauce.getName()).thenReturn("chili sauce");
        when(mockSauce.getPrice()).thenReturn(300f);
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);

        when(mockFilling.getName()).thenReturn("sausage");
        when(mockFilling.getPrice()).thenReturn(300f);
        when(mockFilling.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        String expectedReceipt = "(==== black bun ====)\r\n" +
                "= sauce chili sauce =\r\n" +
                "= filling sausage =\r\n" +
                "(==== black bun ====)\r\n" +
                "\r\n" +
                "Price: 900,000000\r\n";

        assertEquals("Чек бургера должен быть верным", expectedReceipt, burger.getReceipt());
    }
}