package service;

import dao.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import ua.goit.java.service.OrderService;

public class TestOrderService extends Assert{

    private TestConfig testConfig = new TestConfig();

    private OrderService orderService = testConfig.getConfig().orderService();

    @Test
    public void testGetAllOrders(){
        assertNotNull(orderService.getAllOrders());
    }


}
