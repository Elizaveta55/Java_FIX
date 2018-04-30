package com.company.controllers;

import com.company.Services.LoginService;
import com.company.forms.LoginForm;
import com.company.models.Token;
import com.company.transfer.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginForm form){
        return ResponseEntity.ok(service.login(form));
    }

}
