package com.company;

import com.company.repositories.GoodRepository;
import com.company.repositories.GoodRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();

        EntityManager entityManager =
                Persistence.createEntityManagerFactory("com.company.persistence")
                        .createEntityManager();

        GoodRepository repository = new GoodRepositoryImpl(entityManager);

        context.setAttribute("goodRepository", repository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}