package com.company.services;

import com.company.models.GoodModel;
import com.company.repositories.GoodRepository;

import java.util.List;

public class GoodService {

    private GoodRepository goodRepository;

    public GoodService(GoodRepository repository){
        this.goodRepository = repository;
    }

    public void registerGood(GoodModel model){
//        long id = model.getId();
//        GoodModel temp = goodRepository.find(id);
//        if (temp != null){
//            throw new IllegalArgumentException("Already exist");
//        }
        goodRepository.save(model);
    }

    public void updateGood(GoodModel model){
        long id = model.getId();
        GoodModel temp = goodRepository.find(id);
        if (temp != null){
            goodRepository.update(model);
        }
        else{
            throw new IllegalArgumentException("There is no such subject");
        }
    }

    public GoodModel finrById(long id){
        return goodRepository.find(id);
    }

    public List<GoodModel> showAll()
    {
        List<GoodModel> temp = goodRepository.findAll();
        if (temp.size() > 0){
            return temp;
        }
        return null;
    }
}
