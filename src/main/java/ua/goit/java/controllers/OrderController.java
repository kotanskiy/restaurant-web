package ua.goit.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.java.model.Order;
import ua.goit.java.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

@Controller
public class OrderController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String getAllCustomerOrders(Map<String, Object> model){
        model.put("orders", orderService.getAllOrders());
        return "historyorders";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String filter(Map<String, Object> model, HttpServletRequest request) throws ParseException {
        String name = request.getParameter("name").trim();
        String surname = request.getParameter("surname").trim();
        String date = request.getParameter("date").trim();
        String numberTable = request.getParameter("number_table").trim();
        List<Order> orders = new ArrayList<>();
        if (name.equals("") && surname.equals("") && date.equals("") && numberTable.equals("")){
            model.put("exception", "Пустая строка");
            return "error";
        }else {
            try {
                int numberTableInt = Integer.parseInt(numberTable);
                orders.addAll(orderService.getOrdersByNumberTable(numberTableInt));
            }catch (NumberFormatException ignored){}

            if (!name.isEmpty() && !surname.isEmpty()){
                orders.addAll(orderService.getOrdersByEmployee(name, surname));
            }

            if (!date.isEmpty()){
                orders.addAll(orderService.getOrdersByDate(date));
            }
            if (orders.isEmpty()){
                throw new RuntimeException();
            }
            removeDuplicates(orders);
            model.put("orders", orders);
        }
        return "historyorders";
    }

    private static int removeDuplicates(List<Order> orders) {

        int size = orders.size();
        int duplicates = 0;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (orders.get(j).getId() != orders.get(i).getId())
                    continue;
                duplicates++;
                orders.remove(j);
                j--;
                size--;
            }
        }
        return duplicates;

    }

    @RequestMapping(value = "/order/delete/{idOrder}", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable int idOrder){
        orderService.deleteOrder(idOrder);
        return "redirect: /restaurant/order";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleDBExeption(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", "заказ не найден");
        return modelAndView;
    }
}
