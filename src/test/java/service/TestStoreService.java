package service;

import dao.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import ua.goit.java.service.StoreService;

public class TestStoreService extends Assert{

    private TestConfig testConfig = new TestConfig();

    private StoreService storeService = testConfig.getConfig().storeService();

    @Test
    public void testGetAllIngredientsInStore(){
        assertNotNull(storeService.getAllIngredientsInStore());
    }

    @Test
    public void testGetAllIngredients(){
        assertNotNull(storeService.getAllIngredients());
    }

    @Test
    public void testGetIngredientById(){
        assertNotNull(storeService.getIngredientById(1));
    }
}
