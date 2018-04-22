package com.company.services;

import com.company.forms.GoodForm;
import com.company.models.Good;
import com.company.repositories.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodRepository repository;

    @Override
    public void addDelUpd(GoodForm form) {
        Good goodtemp = repository.findByName(form.getName());
        Good newGood = Good.builder()
                .name(form.getName())
                .amount(form.getAmount())
                .build();
        if (goodtemp != null && form.getAmount() != 0) {
            repository.delete(goodtemp);
            repository.save(newGood);
        } else if (goodtemp != null && form.getAmount() == 0) {
            repository.delete(goodtemp);
        } else {
            repository.save(newGood);
        }
    }
}
