package ua.goit.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.goit.java.model.Dish;
import ua.goit.java.model.DishWithCount;
import ua.goit.java.model.Menu;
import ua.goit.java.service.MenuService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MenuController {

    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String getAllMenu(Map<String, Object> model){
        model.put("allMenu", menuService.getAll());
        return "menu";
    }


    @RequestMapping(value = "/menudetails/{id}", method = RequestMethod.GET)
    public String menuDetails(@PathVariable int id ,Map<String, Object> model){
        List<Dish> dishs = menuService.getDishesInMenu(id);
        List<DishWithCount> dishsWithCounts = new ArrayList<>();
        for (Dish d: dishs) {
            DishWithCount dish = new DishWithCount();
            dish.setId(d.getId());
            dish.setName(d.getName());
            dish.setCount(menuService.getCountDishes(id, d.getId()));
            dishsWithCounts.add(dish);
        }
        model.put("allDishes", menuService.getAllDishes());
        model.put("dishes", dishsWithCounts);
        model.put("idMenu", id);
        return "menudetails";
    }

    @RequestMapping(value = "/menudetails/{idMenu}/dishinmenudelete/{idDish}", method = RequestMethod.GET)
    public String deleteDishInMenu(@PathVariable int idMenu, @PathVariable int idDish, Map<String, Object> model){
        menuService.deleteDishInMenu(idMenu, idDish);
        return "redirect: /restaurant/menudetails/" + idMenu;
    }

    @RequestMapping(value = "/menudelete/{idMenu}", method = RequestMethod.GET)
    public String deleteMenu(@PathVariable int idMenu, Map<String, Object> model){
        menuService.deleteMenu(idMenu);
        return "redirect: /restaurant/menu";

    }

    @RequestMapping(value = "/addmenu", method = RequestMethod.GET)
    public String addMenu(Map<String, Object> model){
        return "addmenu";
    }

    @RequestMapping(value = "/addmenu", method = RequestMethod.POST)
    public String createMenu(HttpServletRequest request){
        menuService.createMenu(request.getParameter("name"));
        return "redirect: /restaurant/menu";
    }


    @RequestMapping(value = "/menudetails/{idMenu}/adddishinmenu/{idDish}", method = RequestMethod.GET)
    public String addDishInMenu(@PathVariable int idMenu, @PathVariable int idDish){
        menuService.addDishInMenu(idMenu, idDish);
        return "redirect: /restaurant/menudetails/" + idMenu;
    }

    @RequestMapping(value = "/editmenu/{idMenu}", method = RequestMethod.GET)
    public String editMenu(@PathVariable int idMenu, Map<String, Object> model){
        model.put("menu", menuService.getMenu(idMenu));
        return "addmenu";
    }

    @RequestMapping(value = "/editmenu/{idMenu}", method = RequestMethod.POST)
    public String editMenu(@PathVariable int idMenu, HttpServletRequest request, Map<String, Object> model){
        Menu menu = menuService.getMenu(idMenu);
        menu.setName(request.getParameter("name"));
        if (menu.getName().trim().equals("")){
            model.put("exception", "Пустая строка");
            return "error";
        }else {
            menuService.editMenu(menu);
            return "redirect: /restaurant/menu";
        }
    }

}
