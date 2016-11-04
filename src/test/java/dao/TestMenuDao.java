package dao;

import org.junit.Assert;
import org.junit.Test;
import ua.goit.java.dao.menu.MenuDao;

public class TestMenuDao extends Assert{
    private TestConfig testConfig = new TestConfig();

    private MenuDao menuDao = testConfig.getConfig().jdbcMenuDao();

    @Test
    public void testGetMenu(){
        assertNotNull(menuDao.getMenu("Menu#1"));
    }

    @Test
    public void testGetAll(){
        assertNotNull(menuDao.getAll());
    }
}
