package com.company.controllers;

import com.company.Services.GoodService;
import com.company.forms.GoodForm;
import com.company.models.Good;
import com.company.repositories.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GoodController {

    @Autowired
    private GoodRepository repository;

    @Autowired
    private GoodService service;

    @GetMapping("/products")
    public List<Good> getGoodList() {
        return repository.findAll();
    }

    @PostMapping(value = "/products")
    public ResponseEntity<Object> updateProfile(@RequestBody GoodForm form, Authentication authentication) {
        if (! service.addOrDelGood(authentication, form)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok().build();
    }

}
