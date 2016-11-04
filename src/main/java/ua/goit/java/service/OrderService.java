package ua.goit.java.service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.order.OrderDao;
import ua.goit.java.model.Order;

import java.text.ParseException;
import java.util.List;

public class OrderService {

    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Transactional
    public List<Order> getAllOrders(){
        return orderDao.getAllOrders();
    }

    @Transactional
    public List<Order> getOrdersByEmployee(String name, String surname){
        return orderDao.findOrderByEmployee(name, surname);
    }

    @Transactional
    public List<Order> getOrdersByNumberTable(int number){
        return orderDao.findOrderByNumberTable(number);
    }

    @Transactional
    public List<Order> getOrdersByDate(String date) throws ParseException {
        return orderDao.findOrderByDate(date);
    }

    @Transactional
    public void deleteOrder(int idOrder){
        orderDao.delete(idOrder);
    }
}
