package ua.goit.java.dao.dish;

import ua.goit.java.model.Dish;
import ua.goit.java.model.Ingredient;

import java.util.List;

public interface DishDao {
    public void add(Dish dish);
    public void addIngredientInDish(int idDish, int idIngredient, int count);
    public void deleteIngredientInDish(int idDish, int idIngredient);
    public void delete(int id);
    public List<Dish> getDish(String name);
    public List<Dish> getAll();
    public List<Ingredient> getAllIngredients();
    public int getCount(int idDish, int idIngredient);
    public List<Ingredient> getIngredientsInDish(int idDish);
    public void updateDish(Dish dish);
}
