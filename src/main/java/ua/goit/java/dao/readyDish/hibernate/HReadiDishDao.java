package ua.goit.java.dao.readyDish.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.readyDish.ReadyDishDao;
import ua.goit.java.model.ReadyDish;

import java.util.List;

public class HReadiDishDao implements ReadyDishDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addReadyDish(ReadyDish readyDish) {
        sessionFactory.getCurrentSession().save(readyDish);
    }

    @Override
    @Transactional
    public List<ReadyDish> getReadyDishes() {
        return sessionFactory.getCurrentSession().createQuery("from ReadyDish").list();
    }
}
