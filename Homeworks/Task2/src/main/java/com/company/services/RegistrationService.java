package com.company.services;

import com.company.models.User;
import com.company.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class RegistrationService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<User> showAll() {
        List<User> temp = userRepository.findAll();
        if (temp.size() > 0){
            return temp;
        }
        return null;
    }

    public User finrById(long id){
        return userRepository.find(id);
    }

    public User findByName(String name){
        return userRepository.findOneByName(name);
    }

    public boolean save(String name, String password) {
        if (userRepository.findOneByName(name) == null) {
            User user = User.builder()
                    .name(name)
                    .password(passwordEncoder.encode(password))
                    .build();
            userRepository.save(user);
            return true;
        }
        return false;
    }

}
