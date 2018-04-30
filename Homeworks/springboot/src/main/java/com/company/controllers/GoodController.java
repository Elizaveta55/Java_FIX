package com.company.controllers;

import com.company.forms.GoodForm;
import com.company.models.Good;
import com.company.repositories.GoodRepository;
import com.company.services.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GoodController {

    @Autowired
    private GoodRepository repository;

    @Autowired
    private GoodService service;

    @GetMapping("/products")
    public String getGoodList(@ModelAttribute("model") ModelMap model,  @RequestParam(value = "error", required = false) String error) {
        if (error != null){
            model.addAttribute("error", true);
        }
        List<Good> goods = repository.findAll();
        model.addAttribute("goods", goods);
        return "products";
    }

    @PostMapping(value = "/products")
    public String updateProfile(@ModelAttribute("model") ModelMap model, @ModelAttribute GoodForm form, @RequestParam(value = "error", required = false) String error, Authentication authentication) {
        if (! service.addDelUpd(form, authentication)){
            model.addAttribute("error", true);
        }
        List<Good> goods = repository.findAll();
        model.addAttribute("goods", goods);
        return "products";
    }

}
