package ua.goit.java.dao.employee.hibernate;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.employee.EmployeeDao;
import ua.goit.java.model.Employee;

import java.util.List;

public class HEmployeeDao implements EmployeeDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public HEmployeeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Employee> getAll(){
        List<Employee> employees = sessionFactory.getCurrentSession().createQuery("from Employee").list();
        return employees;
    }

    @Override
    @Transactional
    public List<Employee> getEmployee(String name) {
        return sessionFactory.getCurrentSession().createQuery("from Employee where name = :name").setParameter("name", name).list();
    }


    @Override
    @Transactional
    public void add(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Query query =  sessionFactory.getCurrentSession().createQuery("delete Employee where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void update(Employee employee){
        sessionFactory.getCurrentSession().update(employee);
    }
}
