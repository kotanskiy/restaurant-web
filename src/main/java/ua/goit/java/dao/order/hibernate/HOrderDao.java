package ua.goit.java.dao.order.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.order.OrderDao;
import ua.goit.java.model.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class HOrderDao implements OrderDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void create(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }

    @Transactional
    @Override
    public void delete(int idOrder) {
        String sqlQuery = "DELETE FROM customer_order WHERE id = ?";
        NativeQuery query = sessionFactory.getCurrentSession().createNativeQuery(sqlQuery);
        query.setParameter(1, idOrder);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void setState(int idOrder, boolean state) {
        Order order = sessionFactory.getCurrentSession().get(Order.class, idOrder);
        order.setState(state);
        sessionFactory.getCurrentSession().update(order);
    }

    @Transactional
    @Override
    public void addDishInOrder(int idOrder, int idDish) {
        String sqlQuery = "INSERT INTO dish_list (id_customer_order, id_dish) VALUES (?, ?)";
        NativeQuery query = sessionFactory.getCurrentSession().createNativeQuery(sqlQuery);
        query.setParameter(1, idOrder);
        query.setParameter(2, idDish);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void deleteDishInOrder(int idOrder, int idDish) {
        String sqlQuery = "DELETE FROM dish_list WHERE id_customer_order = ? AND id_dish = ?";
        NativeQuery query = sessionFactory.getCurrentSession().createNativeQuery(sqlQuery);
        query.setParameter(1, idOrder);
        query.setParameter(2, idDish);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public List<Order> getOpenOrCloseOrders(boolean state) {
        return sessionFactory.getCurrentSession().createQuery("from Order where state =:state").setParameter("state", state).list();
    }

    @Transactional
    @Override
    public List<Order> getAllOrders() {
        return sessionFactory.getCurrentSession().createQuery("from Order").list();
    }

    @Transactional
    @Override
    public List<Order> findOrderByNumberTable(int numberTable){
        return sessionFactory.getCurrentSession().createQuery("from Order where number_table =:numberTable").setParameter("numberTable", numberTable).list();
    }

    @Transactional
    @Override
    public List<Order> findOrderByEmployee(String name, String surname){
        List<Order> orders = getAllOrders();
        return orders.stream().filter(o -> o.getIdEmployee().getName().equals(name) && o.getIdEmployee().getSurname().equals(surname)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<Order> findOrderByDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date docDate= format.parse(date);
        java.sql.Date result = new java.sql.Date(docDate.getTime());
        return sessionFactory.getCurrentSession().createQuery("from Order where order_date =:date").setParameter("date", result).list();
    }
}
