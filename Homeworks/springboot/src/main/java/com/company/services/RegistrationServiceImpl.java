package com.company.services;

import com.company.forms.RegistrationForm;
import com.company.models.Role;
import com.company.models.State;
import com.company.models.User;
import com.company.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean registrationUser(RegistrationForm form) {
        if (form.getPassword().equals(form.getCheckPassword())){
            User newUser = User.builder()
                    .login(form.getLogin())
                    .hasnPassword(passwordEncoder.encode(form.getPassword()))
                    .state(State.ACTIVE)
                    .role(Role.USER)
                    .build();
            usersRepository.save(newUser);
            return true;
        }
        else return false;
    }
}
