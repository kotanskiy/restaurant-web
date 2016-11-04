package ua.goit.java.service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.dish.DishDao;
import ua.goit.java.model.Dish;
import ua.goit.java.model.Ingredient;

import java.util.Comparator;
import java.util.List;

public class DishService {
    private DishDao dishDao;

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Transactional
    public List<Dish> getAllDishes(){
        return dishDao.getAll();
    }

    @Transactional
    public List<Ingredient> getAllIngredients(){
        return dishDao.getAllIngredients();
    }

    @Transactional
    public int getCount(int idDish, int idIngredient){
        return dishDao.getCount(idDish, idIngredient);
    }

    @Transactional
    public List<Ingredient> getIngredientsInDish(int idDish){
        return dishDao.getIngredientsInDish(idDish);
    }

    @Transactional
    public void deleteIngredientInDish(int idDish, int idIngredient){
        dishDao.deleteIngredientInDish(idDish, idIngredient);
    }

    @Transactional
    public void addIngredientInDish(int idDish, int idIngredient){
        dishDao.addIngredientInDish(idDish, idIngredient, 1);
    }

    @Transactional
    public void deleteDish(int idDish){
        dishDao.delete(idDish);
    }

    @Transactional
    public void createDish(String name, String category, float costOf, float weight){
        Dish dish = createDishWithId();
        dish.setName(name);
        dish.setCategory(category);
        dish.setCostOf(costOf);
        dish.setWeight(weight);
        dishDao.add(dish);
    }

    @Transactional
    public Dish getDish(int idDish){
        List<Dish> dishs = dishDao.getAll();
        Dish result = null;
        for (Dish d: dishs) {
            if (d.getId() == idDish){
                result = d;
            }
        }
        return result;
    }

    @Transactional
    public void editDish(Dish dish){
        dishDao.updateDish(dish);
    }

    private Dish createDishWithId(){
        Dish dish = new Dish();
        List<Dish> dishs = dishDao.getAll();
        dishs.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return new Integer(o1.getId()).compareTo(o2.getId());
            }
        });
        int id = 0;
        for (Dish d: dishs) {
            if (d.getId() == id){
                id++;
            }else {
                dish.setId(id);
            }
        }
        dish.setId(id);
        return dish;
    }
}
