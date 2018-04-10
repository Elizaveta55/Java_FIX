package com.company.repositories;

import com.company.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.util.List;

public class UserRepositoryHibernateImpl implements UserRepository {

    private EntityManager manager;

    public UserRepositoryHibernateImpl(SessionFactory factory) {
        manager = factory.createEntityManager();
    }

    @Override
    public User findOneByName(String name) {
        return manager.find(User.class, name);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> humanQuery = manager.createQuery("from usery", User.class);
        List<User> humanList = humanQuery.getResultList();
        return humanList;
    }

    @Override
    public void save(User model) {
        manager.getTransaction().begin();
        manager.persist(model);
        manager.getTransaction().commit();
    }

    @Override
    public void update(User model) {
        manager.getTransaction().begin();
        manager.refresh(model);
        manager.getTransaction().commit();
    }

    @Override
    public void delete(User model) {
        manager.getTransaction().begin();
        manager.remove(find(model.getId()));
        manager.getTransaction().commit();
    }

    @Override
    public User find(long userId) {
        return manager.find(User.class, userId);
    }
}
