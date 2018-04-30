package com.company.controllers;

import com.company.forms.RegistrationForm;
import com.company.services.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {

    @Autowired
    private RegistrationService service;

    @GetMapping
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(RegistrationForm userForm) {
        if (service.registrationUser(userForm)){
            return "redirect:/login";
        } else{
            return "signUp";
        }
    }

}
