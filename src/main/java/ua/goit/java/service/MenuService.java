package ua.goit.java.service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.dish.DishDao;
import ua.goit.java.dao.menu.MenuDao;
import ua.goit.java.model.Dish;
import ua.goit.java.model.Menu;

import java.util.Comparator;
import java.util.List;

public class MenuService {

    private MenuDao menuDao;

    private DishDao dishDao;

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Transactional
    public List<Menu> getAll(){
        return menuDao.getAll();
    }

    @Transactional
    public List<Dish> getDishesInMenu(int id){
         return menuDao.getDishesInMenu(id);
    }

    @Transactional
    public int getCountDishes(int idMenu, int idDish){
        return menuDao.getCount(idMenu, idDish);
    }

    @Transactional
    public void deleteDishInMenu(int idMenu, int idDish){
        menuDao.deleteDishInMenu(idMenu, idDish);
    }

    @Transactional
    public void deleteMenu(int id){
        menuDao.delete(id);
    }

    @Transactional
    public void createMenu(String name){
        Menu menu = createMenuWithId();
        menu.setName(name);
        menuDao.create(menu.getId(), menu.getName());
    }

    @Transactional
    public List<Dish> getAllDishes(){
        return dishDao.getAll();
    }

    @Transactional
    public void addDishInMenu(int idMenu, int idDish){
        menuDao.addDishInMenu(idMenu, idDish);
    }

    @Transactional
    public Menu getMenu(int id){
        return menuDao.getMenuById(id);
    }

    private Menu createMenuWithId(){
        Menu menu = new Menu();
        List<Menu> menus = menuDao.getAll();
        menus.sort(new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return new Integer(o1.getId()).compareTo(o2.getId());
            }
        });
        int id = 0;
        for (Menu m: menus) {
            if (m.getId() == id){
                id++;
            }else {
                menu.setId(id);
            }
        }
        menu.setId(id);
        return menu;
    }

    public void editMenu(Menu menu) {
        menuDao.updateMenu(menu);
    }
}
