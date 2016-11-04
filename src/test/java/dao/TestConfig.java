package dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.goit.java.config.AppConfig;

public class TestConfig {

    private AppConfig appConfig = new AppConfig();

    public AppConfig getConfig(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        return context.getBean(AppConfig.class);
    }
}
