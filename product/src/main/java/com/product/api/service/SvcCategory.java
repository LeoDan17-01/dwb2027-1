package com.product.api.service;

import com.product.api.entity.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

// Cambiamos a ResponseEntity
public interface SvcCategory {
    ResponseEntity<List<Category>> getCategories();
}