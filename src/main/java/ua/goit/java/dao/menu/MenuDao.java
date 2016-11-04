package ua.goit.java.dao.menu;

import ua.goit.java.model.Dish;
import ua.goit.java.model.Menu;

import java.util.List;

public interface MenuDao {
    public Menu create(int id, String name);
    public void delete(int id);
    public void addDishInMenu(int idMenu, int idDish);
    public void deleteDishInMenu(int idMenu, int idDish);
    public List<Menu> getMenu(String name);
    public List<Menu> getAll();
    public List<Dish> getDishesInMenu(int idMenu);
    public int getCount(int idMenu, int idDish);
    public Menu getMenuById(int id);

    void updateMenu(Menu menu);
}
