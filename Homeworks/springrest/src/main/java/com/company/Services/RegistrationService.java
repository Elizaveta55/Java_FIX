package com.company.Services;

import com.company.forms.RegistrationForm;
import com.company.models.User;

import java.util.List;

public interface RegistrationService {
    void signUp(RegistrationForm userForm);
    List<User> findAll();
    User findOne(Long userId);
}
