package com.product.api.service;

import com.product.api.entity.Category;
import com.product.api.dto.DtoCategoryIn;

import org.springframework.http.ResponseEntity;

import java.util.List;

// Cambiamos a ResponseEntity
public interface SvcCategory {
    ResponseEntity<List<Category>> findAll();

    //Agreamos los nuevos metodos
    ResponseEntity<List<Category>> findActive();

    //ResponseEntity<List<Category>> findChilds(Integer id);

    //ResponseEntity<Void> create(DtoCategoryIn in);
    
    //ResponseEntity<Void> update(DtoCategoryIn in, Integer id);
    
    //ResponseEntity<Void> enable(Integer id);
    
    //ResponseEntity<Void> disable(Integer id);
}