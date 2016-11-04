package ua.goit.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.java.model.Dish;
import ua.goit.java.model.Employee;
import ua.goit.java.model.Ingredient;
import ua.goit.java.model.IngredientWithCount;
import ua.goit.java.service.DishService;
import ua.goit.java.service.EmployeeService;
import ua.goit.java.service.MenuService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    private DishService dishService;

    private MenuService menuService;

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String index(Map<String, Object> model){
        int idMenu = 1;
        List<Dish> dishesInMenu = menuService.getDishesInMenu(idMenu);
        List<Dish> dishs = dishService.getAllDishes();
        List<Dish> result = new ArrayList<>();
        for (Dish d: dishesInMenu) {
            result.addAll(dishs.stream().filter(m -> m.getId() == d.getId()).collect(Collectors.toList()));
        }
        model.put("allDishes", result);
        return "client/index";
    }

    @RequestMapping(value = "main", method = RequestMethod.POST)
    public String index(HttpServletRequest request, Map<String, Object> model){
        String dishName = request.getParameter("name");
        if (dishName.trim().equals("")){
            model.put("exception", "пустая строка");
            return "client/errorclient";
        }else {
            int idMenu = 1;
            List<Dish> dishesInMenu = menuService.getDishesInMenu(idMenu);
            List<Dish> dishs = dishService.getAllDishes();
            List<Dish> result = new ArrayList<>();
            List<Dish> findResult = new ArrayList<>();
            for (Dish d: dishesInMenu) {
                result.addAll(dishs.stream().filter(m -> m.getId() == d.getId()).collect(Collectors.toList()));
            }
            findResult.addAll(result.stream().filter(d -> d.getName().equals(dishName)).collect(Collectors.toList()));
            if (findResult.isEmpty()){
                model.put("exception", "Такого блюда нет");
                return "client/errorclient";
            }else {
                model.put("allDishes", findResult);
                return "client/index";
            }

        }

    }

    @RequestMapping(value = "scheme", method = RequestMethod.GET)
    public String getScheme(){
        return "client/scheme";
    }

    @RequestMapping(value = "main/dishdetails/{idDish}", method = RequestMethod.GET)
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
        model.put("dish", dishService.getDish(idDish));
        return "client/dishdetails";
    }

    @RequestMapping(value = "employees", method = RequestMethod.GET)
    public String getEmployees(Map<String, Object> model){
        List<Employee> employees = employeeService.getAllEmployees();
        List<Employee> waiters = employees.stream().filter(e -> e.getPosition().equals("waiter")).collect(Collectors.toCollection(LinkedList::new));
        model.put("allEmployees", waiters);
        return "client/employee";
    }

    @RequestMapping(value = "contacts", method = RequestMethod.GET)
    public String getContacts(){
        return "client/contacts";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleDBExeption(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", "У этого сотрудника не выполнен заказ");
        return modelAndView;
    }
}
