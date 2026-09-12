package com.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CtrlProduct {
    
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
        return categories;
    }
}