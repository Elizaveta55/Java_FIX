package com.company;

import com.company.repositories.GoodRepository;
import com.company.repositories.GoodRepositoryImpl;
import com.company.repositories.UserRepository;
import com.company.repositories.UserRepositoryImpl;
import com.company.services.GoodService;
import com.company.services.RegistrationService;
import com.company.services.LoginService;
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

        GoodRepository goodRepository = new GoodRepositoryImpl(dataSource);
        GoodService goodService = new GoodService(goodRepository);

        UserRepository userRepository = new UserRepositoryImpl(dataSource);
        RegistrationService userService = new RegistrationService(userRepository);

        LoginService loginServicetemp = new LoginService(userRepository);

        servletContextEvent.getServletContext().setAttribute("goodService", goodService);
        servletContextEvent.getServletContext().setAttribute("userService", userService);
        servletContextEvent.getServletContext().setAttribute("LoginService", loginServicetemp);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}