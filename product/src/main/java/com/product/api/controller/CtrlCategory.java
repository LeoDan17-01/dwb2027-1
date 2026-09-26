package com.product.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CtrlCategory {

    // Inyeccion de dependencias del servicio
    @Autowired
    SvcCategory svc;
    

    @GetMapping
    public List<Category> getCategories() {
        return svc.getCategories();
    }
}