package ua.goit.java.dao.order;

import ua.goit.java.model.Order;

import java.text.ParseException;
import java.util.List;

public interface OrderDao {
    public void create(Order order);
    public void delete(int idOrder);
    public void setState(int idOrder, boolean state);
    public void addDishInOrder(int idOrder, int idDish);
    public void deleteDishInOrder(int idOrder, int idDish);
    public List<Order> getOpenOrCloseOrders(boolean state);
    public List<Order> getAllOrders();
    public List<Order> findOrderByNumberTable(int numberTable);
    public List<Order> findOrderByEmployee(String name, String surname);
    public List<Order> findOrderByDate(String date) throws ParseException;
}
