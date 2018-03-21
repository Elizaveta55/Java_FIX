package com.company.services;

import com.company.models.User;
import com.company.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class LoginService {

    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public LoginService(UserRepository userRepository){
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userRepository = userRepository;
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

    public boolean check(String name, String password) {

        User user = userRepository.findOneByName(name);
        if (user != null && passwordEncoder.matches(password, user.getPassword())){
            return true;
        }
        return false;
    }
}
