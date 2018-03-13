package com.company.repositories;

import com.company.models.GoodModel;

import javax.persistence.EntityManager;
import java.util.List;

public class GoodRepositoryImpl implements GoodRepository {

    private EntityManager entityManager;

    public GoodRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<GoodModel> findAll() {
        return entityManager.createQuery("from GoodModel", GoodModel.class).getResultList();
    }

    @Override
    public void save(GoodModel model) {
        entityManager.getTransaction().begin();
        entityManager.persist(model);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(GoodModel model) {
        entityManager.getTransaction().begin();
        entityManager.refresh(model);
        entityManager.getTransaction().commit();
    }

    @Override
    public GoodModel find(long Id) {
        return entityManager.find(GoodModel.class, Id);
    }
}
