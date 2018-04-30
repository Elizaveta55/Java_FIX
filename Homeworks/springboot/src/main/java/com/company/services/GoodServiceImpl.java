package com.company.services;

import com.company.forms.GoodForm;
import com.company.models.Good;
import com.company.models.Role;
import com.company.models.State;
import com.company.models.User;
import com.company.repositories.GoodRepository;
import com.company.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodRepository repository;

    @Autowired
    private UserRepository usersRepository;

    @Override
    public boolean addDelUpd(GoodForm form, Authentication authentication) {
        User user = usersRepository.findByLogin(authentication.getName()).orElseThrow(IllegalArgumentException::new);
        Good newGood = Good.builder()
                .name(form.getName())
                .amount(form.getAmount())
                .appUser(user)
                .build();
        if (repository.findByName(form.getName()).isPresent()) {
            Good goodtemp = repository.findByName(form.getName()).get();

            if (form.getAmount() != 0) {
                repository.delete(goodtemp);
                repository.save(newGood);
                return true;
            } else
                if (form.getAmount() == 0) {
                if (goodtemp.getAppUser().equals(newGood.getAppUser()) || user.getRole().equals(Role.ADMIN)){
                    repository.delete(goodtemp);
                    return true;
                }
                else return false;
            }
        }
        else {
            repository.save(newGood);
            return true;
        }
        return true;
    }
}
