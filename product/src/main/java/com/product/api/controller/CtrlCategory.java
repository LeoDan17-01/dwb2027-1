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
    
    private final List<Category> categories = new ArrayList<>();

    {
        categories.add(new Category(1, "Ropa", "RP", null, 1));

        categories.add(new Category(2, "Calzado", "CLZD", 1, 1));

        categories.add(new Category(3, "Electronica", "ELEC", null, 1));

        categories.add(new Category(4, "Celulares", "CEL", 3, 1));

        categories.add(new Category(5, "Accesorios", "ACC", 1, 1));
    }

    @GetMapping
    public List<Category> getCategories() {
        return svc.getCategories();
    }
}