package ua.goit.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.java.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getEmployees(Map<String, Object> model){
        model.put("allEmployees", employeeService.getAllEmployees());
        return "employee";
    }

    @RequestMapping(value = "/addemployee", method = RequestMethod.GET)
    public String addEmployee(){
        return "addemployee";
    }

    @RequestMapping(value = "/addemployee", method = RequestMethod.POST)
    public String addEmployeeWithParametrs(HttpServletRequest request){
        employeeService.addEmployee(request);
        return "redirect: /restaurant/employee";
    }

    @RequestMapping(value = "/employeedelete/{idEmployee}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable int idEmployee){
        employeeService.deleteEmployee(idEmployee);
        return "redirect: /restaurant/employee";
    }

    @RequestMapping(value = "/editemployee/{idEmployee}", method = RequestMethod.GET)
    public String editEmployee(@PathVariable int idEmployee, Map<String, Object> model){
        model.put("employee", employeeService.getEmployeeById(idEmployee));
        return "addemployee";
    }

    @RequestMapping(value = "/editemployee/{idEmployee}", method = RequestMethod.POST)
    public String editEmployee(@PathVariable int idEmployee, HttpServletRequest request){
        employeeService.updateEmployee(idEmployee, request);
        return "redirect: /restaurant/employee";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleDBExeption(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", "Не валидный ввод");
        return modelAndView;
    }
}
