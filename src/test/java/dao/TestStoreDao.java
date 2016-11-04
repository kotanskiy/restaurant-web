package dao;

import org.junit.Assert;
import org.junit.Test;
import ua.goit.java.dao.store.StoreDao;

public class TestStoreDao extends Assert{

    private TestConfig testConfig = new TestConfig();

    private StoreDao storeDao = testConfig.getConfig().hStoreDao();

    @Test
    public void testGetIngredientByName(){
        assertNotNull(storeDao.getIngredient("chiken"));
    }

    @Test
    public void testGetAllIngredients(){
        assertNotNull(storeDao.getAllIngredients());
    }

    @Test
    public void testGetAllIngredientsInStore(){
        assertNotNull(storeDao.getAllIngredientsInStore());
    }


}
