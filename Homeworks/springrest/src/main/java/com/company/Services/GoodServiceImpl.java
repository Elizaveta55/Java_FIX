package com.company.Services;


import com.company.forms.GoodForm;
import com.company.models.Good;
import com.company.models.Role;
import com.company.models.User;
import com.company.repositories.GoodRepository;
import com.company.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodRepository repository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public boolean addOrDelGood(Authentication authentication, GoodForm form) {
        User user = usersRepository.findOneByLogin(authentication.getName()).orElseThrow(IllegalArgumentException::new);
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
