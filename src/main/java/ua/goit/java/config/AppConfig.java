package ua.goit.java.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ua.goit.java.dao.dish.jdbc.JdbcDishDao;
import ua.goit.java.dao.employee.hibernate.HEmployeeDao;
import ua.goit.java.dao.menu.jdbc.JdbcMenuDao;
import ua.goit.java.dao.order.hibernate.HOrderDao;
import ua.goit.java.dao.store.hibernate.HStoreDao;
import ua.goit.java.service.*;

@Configuration
@Import({DataBaseConfig.class})
@ComponentScan("ua.goit.java")
public class AppConfig {

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Autowired
    private HibernateTransactionManager txManager;

    @Autowired
    private ComboPooledDataSource comboPooledDataSource;

    @Bean
    public HEmployeeDao hEmployeeDao(){
        return new HEmployeeDao(txManager.getSessionFactory());
    }

    @Bean
    public HStoreDao hStoreDao(){
        HStoreDao hStoreDao = new HStoreDao();
        hStoreDao.setSessionFactory(txManager.getSessionFactory());
        return hStoreDao;
    }

    @Bean
    public HOrderDao hOrderDao(){
        HOrderDao hOrderDao = new HOrderDao();
        hOrderDao.setSessionFactory(txManager.getSessionFactory());
        return hOrderDao;
    }

    @Bean
    public JdbcDishDao jdbcDishDao(){
        JdbcDishDao jdbcDishDao = new JdbcDishDao();
        jdbcDishDao.dataSource = comboPooledDataSource;
        return jdbcDishDao;
    }

    @Bean
    public JdbcMenuDao jdbcMenuDao(){
        JdbcMenuDao jdbcMenuDao = new JdbcMenuDao();
        jdbcMenuDao.dataSource = comboPooledDataSource;
        return jdbcMenuDao;
    }



    @Bean
    public OrderService orderService(){
        OrderService orderService = new OrderService();
        orderService.setOrderDao(hOrderDao());
        return orderService;
    }

    @Bean
    public StoreService storeService(){
        StoreService storeService = new StoreService();
        storeService.setStoreDao(hStoreDao());
        return storeService;
    }

    @Bean
    public EmployeeService employeeService(){
        EmployeeService employeeService = new EmployeeService();
        employeeService.setEmployeeDao(hEmployeeDao());
        return employeeService;
    }

    @Bean
    public MenuService menuService(){
        MenuService menuService = new MenuService();
        menuService.setMenuDao(jdbcMenuDao());
        menuService.setDishDao(jdbcDishDao());
        return menuService;
    }

    @Bean
    public DishService dishService(){
        DishService dishService = new DishService();
        dishService.setDishDao(jdbcDishDao());
        return dishService;
    }
}
