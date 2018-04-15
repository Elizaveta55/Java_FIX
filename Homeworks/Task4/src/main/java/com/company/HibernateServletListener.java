package com.company;

import com.company.models.User;
import com.company.repositories.*;
import com.company.services.GoodService;
import com.company.services.RegistrationService;
import com.company.services.LoginService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HibernateServletListener implements ServletContextListener {

    private Properties properties;

    private String hibernateDialect;
    private String hibernateDdlAuto;
    private String hibernateShowSql;
    private String hibernateDriverClass;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private String dbDriver;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\Елизавета\\Desktop\\Java\\JAVA_FIX\\Homeworks\\Task1\\application.properties"));

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        dbUrl = properties.getProperty("URL");
        dbPassword = properties.getProperty("password");
        dbUser = properties.getProperty("user");
        dbDriver = properties.getProperty("driverName");
        hibernateShowSql = properties.getProperty("hibernate.show_sql");
        hibernateDialect = properties.getProperty("hibernate.dialect");
        hibernateDdlAuto = properties.getProperty("hibernate.hbm2ddl.auto");
        hibernateDriverClass = properties.getProperty("hibernate.connection.driver_class");


        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);


        ClassPathXmlApplicationContext contextXml = new ClassPathXmlApplicationContext("com.company/context.xml");
        GoodService goodService = contextXml.getBean(GoodService.class);

        AnnotationConfigApplicationContext contextJava= new AnnotationConfigApplicationContext(JavaApplicationContext.class);
        RegistrationService userServiceJDBC = contextJava.getBean(RegistrationService.class);
        LoginService loginServicetemp = contextJava.getBean(LoginService.class);

//        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        RegistrationService userServiceHibernate = contextXml.getBean(RegistrationService.class);


        servletContextEvent.getServletContext().setAttribute("goodService", goodService);
        servletContextEvent.getServletContext().setAttribute("userService", userServiceHibernate);
        servletContextEvent.getServletContext().setAttribute("LoginService", loginServicetemp);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}