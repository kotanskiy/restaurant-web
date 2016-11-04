package dao;

import org.junit.Assert;
import org.junit.Test;
import ua.goit.java.dao.order.OrderDao;

public class TestOrderDao extends Assert{
    private TestConfig testConfig = new TestConfig();

    private OrderDao orderDao = testConfig.getConfig().hOrderDao();

    @Test
    public void testGetOpenOrders(){
        assertNotNull(orderDao.getOpenOrCloseOrders(true));
    }

    @Test
    public void testGetCloseOrders(){
        assertNotNull(orderDao.getOpenOrCloseOrders(false));
    }
}
