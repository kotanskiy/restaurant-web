package ua.goit.java.service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.store.StoreDao;
import ua.goit.java.model.Ingredient;
import ua.goit.java.model.Store;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreService {

    private StoreDao storeDao;

    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @Transactional
    public List<Store> getAllIngredientsInStore() {
        return storeDao.getAllIngredientsInStore();
    }

    @Transactional
    public List<Ingredient> getAllIngredients(){
        List<Ingredient> removeCollection = new ArrayList<>();
        List<Store> stores = getAllIngredientsInStore();
        List<Ingredient> ingredients = storeDao.getAllIngredients();
        for (Store s: stores) {
            removeCollection.addAll(ingredients.stream().filter(i -> s.getIdIngredient().getId() == i.getId()).collect(Collectors.toList()));
        }
        ingredients.removeAll(removeCollection);
        return ingredients;
    }

    @Transactional
    public void addIngredientInStore(HttpServletRequest request) {
        storeDao.addIngredientInStore(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("count")));
    }

    @Transactional
    public void deleteIngredientInStore(int idIngredient){
        storeDao.deleteIngredientInStore(idIngredient);
    }

    @Transactional
    public List<Store> getIngredientByName(HttpServletRequest request){
        List<Store> result = new ArrayList<>();
        List<Store> stores = storeDao.getAllIngredientsInStore();
        result.addAll(stores.stream().filter(s -> s.getIdIngredient().getName().equals(request.getParameter("name"))).collect(Collectors.toList()));
        return result;
    }

    @Transactional
    public List<Ingredient> getIngredientById(int idIngredient){
        List<Store> ingredientsInStore = getAllIngredientsInStore();
        return ingredientsInStore.stream().filter(s -> s.getIdIngredient().getId() == idIngredient).map(Store::getIdIngredient).collect(Collectors.toList());
    }

    @Transactional
    public int getCountIngredientsInStore(int idIngredient){
        int count = 0;
        List<Store> ingredientsInStore = getAllIngredientsInStore();
        for (Store s: ingredientsInStore) {
            if (s.getIdIngredient().getId() == idIngredient){
                count = s.getCount();
            }
        }
        return count;
    }

    @Transactional
    public void setCountIngredient(int idIngredient, int count){
        storeDao.setIngredientsInStore(idIngredient, count);
    }
}
