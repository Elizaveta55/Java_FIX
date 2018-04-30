package com.company.controllers;

import com.company.Services.RegistrationService;
import com.company.forms.RegistrationForm;
import com.company.models.User;
import com.company.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.company.transfer.UserDto.from;

@RestController
public class UsersController {
    @Autowired
    private RegistrationService service;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return from(service.findAll());
    }

    @GetMapping("/users/{user-id}")
    public User getUser(@PathVariable("user-id") Long userId) {
        return service.findOne(userId);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody RegistrationForm userForm) {
        service.signUp(userForm);
        return ResponseEntity.ok().build();
    }
}