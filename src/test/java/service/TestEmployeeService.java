package service;

import dao.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import ua.goit.java.service.EmployeeService;

public class TestEmployeeService extends Assert{
    private TestConfig testConfig = new TestConfig();

    private EmployeeService employeeService = testConfig.getConfig().employeeService();

    @Test
    public void testGetEmployee(){
        assertNotNull(employeeService.getEmployeeById(2));
    }

    @Test
    public void testGetAllEmployee(){
        assertNotNull(employeeService.getAllEmployees());
    }


}
