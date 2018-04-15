package com.company.services;

import com.company.models.Good;
import com.company.repositories.GoodRepositoryJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodService {


    private GoodRepositoryJDBC goodRepositoryJDBC;


    public GoodService(GoodRepositoryJDBC repository) {
        this.goodRepositoryJDBC = repository;
    }

    public void registerGood(Good model) {
        Good temp = goodRepositoryJDBC.findOneByName(model.getName());
        if (temp != null) {
            throw new IllegalArgumentException("Already exist");
        }
        goodRepositoryJDBC.save(model);
    }

    public void updateGood(Good model) {
        Good temp = goodRepositoryJDBC.findOneByName(model.getName());
        if (temp != null) {
            model.setId(temp.getId());
            goodRepositoryJDBC.update(model);
        } else {
            throw new IllegalArgumentException("There is no such subject");
        }
    }

    public Good finrById(long id) {
        return goodRepositoryJDBC.find(id);
    }

    public List<Good> showAll() {
        List<Good> temp = goodRepositoryJDBC.findAll();
        if (temp.size() > 0) {
            return temp;
        }
        return null;
    }

    public void workWithGood(Good good) {
        Good goodTemp = goodRepositoryJDBC.findOneByName(good.getName());
        if (goodTemp != null && good.getAmount() != 0) {
            this.updateGood(good);
        } else if (goodTemp != null && good.getAmount() == 0) {
            good.setId(goodTemp.getId());
            goodRepositoryJDBC.delete(good);
        } else {
            this.registerGood(good);
        }
    }
}
