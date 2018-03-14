package com.company;

import com.company.repositories.GoodRepository;
import com.company.repositories.GoodRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("com.company.persistence");

        GoodRepository goodRepository = new GoodRepositoryImpl(factory.createEntityManager());

        servletContextEvent.getServletContext().setAttribute("goodRepository", goodRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}