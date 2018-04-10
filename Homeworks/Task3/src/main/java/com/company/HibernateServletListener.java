package com.company;

import com.company.models.User;
import com.company.repositories.*;
import com.company.services.GoodService;
import com.company.services.RegistrationService;
import com.company.services.LoginService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateServletListener implements ServletContextListener {

    private static final String URL = "jdbc:postgresql://localhost:5432/listOfGoods";
    private static final String USER = "postgres";
    private static final String PASSWORD = "HighLow11";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

        GoodRepositoryJDBC goodRepositoryJDBC = new GoodRepositoryJDBCImpl(dataSource);
        GoodService goodService = new GoodService(goodRepositoryJDBC);

        UserRepository userRepository = new UserRepositoryJdbcTemplateImpl(dataSource);
        RegistrationService userServiceJDBC = new RegistrationService(userRepository);

        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", URL);
        configuration.setProperty("hibernate.connection.username", USER);
        configuration.setProperty("hibernate.connection.password", PASSWORD);
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.addResource("User.hbm.xml");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.addAnnotatedClass(User.class);
        SessionFactory factory = configuration.buildSessionFactory();
        UserRepository repositoryHibernate = new UserRepositoryHibernateImpl(factory);
        RegistrationService userServiceHibernate = new RegistrationService(repositoryHibernate);

        LoginService loginServicetemp = new LoginService(userRepository);

        servletContextEvent.getServletContext().setAttribute("goodService", goodService);
        servletContextEvent.getServletContext().setAttribute("userService", userServiceJDBC);
        servletContextEvent.getServletContext().setAttribute("LoginService", loginServicetemp);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}