package ua.goit.java.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Admin on 29.09.2016.
 */
@Configuration
@PropertySource("classpath:jdbc.properties")
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement(proxyTargetClass = true)
public class DataBaseConfig {

    //db properties
    private static final String JDBC_DRIVER_CLASS = "jdbc.driver.class";
    private static final String JDBC_URL = "jdbc.url";
    private static final String JDBC_USER = "jdbc.user";
    private static final String JDBC_PASSWORD = "jdbc.password";
    private static final String JDBC_MIN_CONNECTIONS = "jdbc.min.connections";
    private static final String JDBC_MAX_CONNECTIONS = "jdbc.max.connections";
    private static final String JDBC_ACQUIRE_INCREMENT = "jdbc.acquire.increment";

    //hibernate properties
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PCKG_TO_SCAN = "entitymanager.packages.to.scan";

    @Autowired
    private Environment env;

    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty(JDBC_DRIVER_CLASS));
        dataSource.setJdbcUrl(env.getProperty(JDBC_URL));
        dataSource.setUser(env.getProperty(JDBC_USER));
        dataSource.setPassword(env.getProperty(JDBC_PASSWORD));

        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty(JDBC_MIN_CONNECTIONS)));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty(JDBC_MAX_CONNECTIONS)));
        dataSource.setAcquireIncrement(Integer.parseInt(env.getProperty(JDBC_ACQUIRE_INCREMENT)));
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        try {
            localSessionFactoryBean.setDataSource(dataSource());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        localSessionFactoryBean.setPackagesToScan(env.getProperty(PCKG_TO_SCAN));
        localSessionFactoryBean.setHibernateProperties(getHibernateProperties());
        try {
            localSessionFactoryBean.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return localSessionFactoryBean.getObject();
    }

    @Bean
    public Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.setProperty(HIBERNATE_DIALECT, env.getProperty(HIBERNATE_DIALECT));
        properties.setProperty(HIBERNATE_SHOW_SQL, env.getProperty(HIBERNATE_SHOW_SQL));
        properties.setProperty(PCKG_TO_SCAN, env.getProperty(PCKG_TO_SCAN));
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        return properties;
    }


    @Bean
    @Autowired
    public HibernateTransactionManager txManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory());
        return hibernateTransactionManager;
    }

    @Bean
    @Autowired
    public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }
}
