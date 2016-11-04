package ua.goit.java.dao.employee;

import ua.goit.java.model.Employee;

import java.util.List;

public interface EmployeeDao {
    public List<Employee> getAll();
    public List getEmployee(String name);
    public void add(Employee employee);
    public void delete(int id);
    public void update(Employee employee);
}
