package dao;


import org.junit.Assert;
import org.junit.Test;
import ua.goit.java.dao.employee.EmployeeDao;

public class TestEmployeeDao extends Assert{

    private TestConfig testConfig = new TestConfig();

    private EmployeeDao employeeDao = testConfig.getConfig().hEmployeeDao();

    @Test
    public void testGetEmployee(){
        assertNotNull(employeeDao.getEmployee("Alex"));
    }

    @Test
    public void testGetAllEmployees(){
        assertNotNull(employeeDao.getAll());
    }

}
