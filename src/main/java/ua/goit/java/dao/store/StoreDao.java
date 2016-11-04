package ua.goit.java.dao.store;

import ua.goit.java.model.Ingredient;
import ua.goit.java.model.Store;

import java.util.List;

public interface StoreDao {
    public void addIngredientInList(String name);
    public void addIngredientInStore(int idIngredient, int count);
    public void deleteIngredientInStore(int idIngredient);
    public void setIngredientsInStore(int idIngredient, int count);
    public Ingredient getIngredient(String name);
    public List<Ingredient> getAllIngredients();
    public List<Store> ingredientsLessThan(int count);
    public List<Store> getAllIngredientsInStore();
}
