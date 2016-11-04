package ua.goit.java.service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.dao.employee.EmployeeDao;
import ua.goit.java.model.Employee;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyVetoException;
import java.util.List;

public class EmployeeService {

    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Transactional
    public List<Employee> getAllEmployees(){
        return employeeDao.getAll();
    }

    @Transactional
    public Employee getEmployeeById(int id){
        List<Employee> employees = employeeDao.getAll();
        Employee employee = null;
        for (Employee e: employees) {
            if (e.getId() == id){
                employee = e;
            }
        }
        return employee;
    }

    @Transactional
    public void addEmployee(HttpServletRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getParameter("name"));
        employee.setSurname(request.getParameter("surname"));
        employee.setDataBirth(request.getParameter("date_birth"));
        employee.setPosition(request.getParameter("position"));
        employee.setSalary(Float.parseFloat(request.getParameter("salary")));
        employee.setPhone(request.getParameter("phone"));
        employeeDao.add(employee);
    }

    @Transactional
    public void deleteEmployee(int idEmployee) {
        employeeDao.delete(idEmployee);
    }

    @Transactional
    public void updateEmployee(int id,HttpServletRequest request){
        Employee employee = new Employee();
        try {
            employee.setId(id);
            employee.setName(request.getParameter("name"));
            employee.setSurname(request.getParameter("surname"));
            employee.setDataBirth(request.getParameter("date_birth"));
            employee.setPosition(request.getParameter("position"));
            employee.setSalary(Float.parseFloat(request.getParameter("salary")));
            employee.setPhone(request.getParameter("phone"));
            if (request.getParameter("phone").trim().length() > 16 || request.getParameter("phone").trim().length() < 16){
                throw new RuntimeException();
            }
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        employeeDao.update(employee);
    }
}
