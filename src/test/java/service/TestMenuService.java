package service;

import dao.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import ua.goit.java.service.MenuService;

public class TestMenuService extends Assert{
    private TestConfig testConfig = new TestConfig();

    private MenuService menuService = testConfig.getConfig().menuService();

    @Test
    public void TestGetAllMenu(){
        assertNotNull(menuService.getAll());
    }

    @Test
    public void TestGetAllDishes(){
        assertNotNull(menuService.getAllDishes());
    }

    @Test
    public void TestGetDishesInMenu(){
        assertNotNull(menuService.getDishesInMenu(0));
    }

    @Test
    public void TestGetMenuById(){
        assertNotNull(menuService.getMenu(1));
    }
}
