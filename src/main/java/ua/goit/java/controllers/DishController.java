package ua.goit.java.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.java.model.Dish;
import ua.goit.java.model.Ingredient;
import ua.goit.java.model.IngredientWithCount;
import ua.goit.java.service.DishService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class DishController {
    private DishService dishService;

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping(value = "/dish", method = RequestMethod.GET)
    public String getAlldishes(Map<String, Object> model){
        model.put("allDishes", dishService.getAllDishes());
        return "dish";
    }

    @RequestMapping(value = "/dishdetails/{idDish}", method = RequestMethod.GET)
    public String getDishDetails(@PathVariable int idDish, Map<String, Object> model){
        List<Ingredient> ingredients = dishService.getIngredientsInDish(idDish);
        List<IngredientWithCount> ingredientWithCounts = new ArrayList<>();
        for (Ingredient i: ingredients) {
            IngredientWithCount ingredientWithCount = new IngredientWithCount();
            ingredientWithCount.setId(i.getId());
            ingredientWithCount.setName(i.getName());
            ingredientWithCount.setCount(dishService.getCount(idDish, i.getId()));
            ingredientWithCounts.add(ingredientWithCount);
        }
        model.put("ingredientsWithCount", ingredientWithCounts);
        model.put("allIngredients", dishService.getAllIngredients());
        model.put("idDish", idDish);
        return "dishdetails";
    }

    @RequestMapping(value = "/dishdetails/{idDish}/ingredientindishdelete/{idIngredient}", method = RequestMethod.GET)
    public String deleteDishInMenu(@PathVariable int idDish, @PathVariable int idIngredient, Map<String, Object> model){
        dishService.deleteIngredientInDish(idDish, idIngredient);
        return "redirect: /restaurant/dishdetails/" + idDish;
    }

    @RequestMapping(value = "/dishdetails/{idDish}/addingredientindish/{idIngredient}", method = RequestMethod.GET)
    public String addDishInMenu(@PathVariable int idDish, @PathVariable int idIngredient){
        dishService.addIngredientInDish(idDish, idIngredient);
        return "redirect: /restaurant/dishdetails/" + idDish;
    }

    @RequestMapping(value = "/dishdelete/{idDish}", method = RequestMethod.GET)
    public String deleteMenu(@PathVariable int idDish, Map<String, Object> model){
        dishService.deleteDish(idDish);
        return "redirect: /restaurant/dish";
    }

    @RequestMapping(value = "/adddish", method = RequestMethod.GET)
    public String addDishView(Map<String, Object> model){
        return "adddish";
    }

    @RequestMapping(value = "/adddish", method = RequestMethod.POST)
    public String createDish(HttpServletRequest request){
        dishService.createDish(request.getParameter("name"), request.getParameter("category"), Float.parseFloat(request.getParameter("costOf")), Float.parseFloat(request.getParameter("weight")));
        return "redirect: /restaurant/dish";
    }


    @RequestMapping(value = "/editdish/{idDish}", method = RequestMethod.GET)
    public String editDish(HttpServletRequest request, Map<String, Object> model, @PathVariable int idDish){
        model.put("dish", dishService.getDish(idDish));
        return "adddish";
    }

    @RequestMapping(value = "/editdish/{idDish}", method = RequestMethod.POST)
    public String editDishWithParametrs(HttpServletRequest request, Map<String, Object> model, @PathVariable int idDish){
        Dish dish = dishService.getDish(idDish);
        dish.setName(request.getParameter("name"));
        dish.setCategory(request.getParameter("category"));
        dish.setCostOf(Float.parseFloat(request.getParameter("costOf")));
        dish.setWeight(Float.parseFloat(request.getParameter("weight")));
        dishService.editDish(dish);
        return "redirect: /restaurant/dishdetails/" + idDish;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleDBExeption(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", "Это блюдо включено в меню");
        return modelAndView;
    }
}
