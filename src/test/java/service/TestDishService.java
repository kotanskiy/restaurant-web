package service;

import dao.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import ua.goit.java.service.DishService;

public class TestDishService extends Assert{
    private TestConfig testConfig = new TestConfig();

    private DishService dishService = testConfig.getConfig().dishService();

    @Test
    public void getAllDishes(){
        assertNotNull(dishService.getAllDishes());
    }

    @Test
    public void getAllIngredients(){
        assertNotNull(dishService.getAllIngredients());
    }

    @Test
    public void getIngredientsInDish(){
        assertNotNull(dishService.getIngredientsInDish(0));
    }

    @Test
    public void getDish(){
        assertNotNull(dishService.getDish(1));
    }
}
