package dao;

import org.junit.Assert;
import org.junit.Test;
import ua.goit.java.dao.dish.DishDao;

public class TestDishDao extends Assert{

    private TestConfig testConfig = new TestConfig();

    private DishDao dishDao = testConfig.getConfig().jdbcDishDao();

    @Test
    public void testGetDish(){
        assertNotNull(dishDao.getDish("spaggeti").get(0));
    }

    @Test
    public void testGetAll(){
        assertNotNull(dishDao.getAll());
    }

    @Test
    public void testGetAllIngredients(){
        assertNotNull(dishDao.getAllIngredients());
    }

    @Test
    public void testGetIngredientsInDish(){
        assertNotNull(dishDao.getIngredientsInDish(0));
    }
}
