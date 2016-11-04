package ua.goit.java.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.goit.java.model.Store;
import ua.goit.java.service.StoreService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class StoreController {

    private StoreService storeService;

    @Autowired
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    @RequestMapping(value = "/store", method = RequestMethod.GET)
    public String getAllIngredients(Map<String, Object> model){
        model.put("ingredients", storeService.getAllIngredientsInStore());
        return "store";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String getIngredient(Map<String, Object> model, HttpServletRequest request){
        List<Store> ingredients = storeService.getIngredientByName(request);
        if (ingredients.isEmpty()){
            model.put("exception", "Такого ингредиента нет");
            return "error";
        }else {
            model.put("ingredients", ingredients);
            return "store";
        }
    }

    @RequestMapping(value = "/addingredientinstore", method = RequestMethod.GET)
    public String addIngredientInStore(Map<String, Object> model){
        model.put("ingredients", storeService.getAllIngredients());
        return "addingredientinstore";
    }

    @RequestMapping(value = "/addingredientinstore", method = RequestMethod.POST)
    public String addIngredientInStore(HttpServletRequest request){
        storeService.addIngredientInStore(request);
        return "redirect: /restaurant/store";
    }

    @RequestMapping(value = "/ingredientdelete/{idIngredient}", method = RequestMethod.GET)
    public String deleteIngredient(@PathVariable int idIngredient){
        storeService.deleteIngredientInStore(idIngredient);
        return "redirect: /restaurant/store";
    }

    @RequestMapping(value = "/editingredient/{idIngredient}", method = RequestMethod.GET)
    public String editIngredient(Map<String, Object> model, @PathVariable int idIngredient){
        model.put("ingredients", storeService.getIngredientById(idIngredient));
        model.put("count", storeService.getCountIngredientsInStore(idIngredient));
        return "addingredientinstore";
    }

    @RequestMapping(value = "/editingredient/{idIngredient}", method = RequestMethod.POST)
    public String editIngredient(@PathVariable int idIngredient, HttpServletRequest request, Map<String, Object> model){
        int count = Integer.parseInt(request.getParameter("count"));
        if (count < 0){
            model.put("exception", "Кол-во не может быть меньше 0");
            return "error";
        }else {
            storeService.setCountIngredient(idIngredient, count);
            return "redirect: /restaurant/store";
        }
    }
}
