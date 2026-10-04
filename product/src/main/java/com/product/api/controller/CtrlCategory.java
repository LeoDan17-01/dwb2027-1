package com.product.api.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;
import com.product.api.dto.DtoCategoryIn;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CtrlCategory {

    // Inyeccion de dependencias del servicio
    @Autowired
    SvcCategory svc;
    
    // Actualizamos el controlador para que devuelva ResponseEntity<List<Category>> en lugar de List<Category>
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return svc.findAll();
    }

    // Agregamos el endpoint para ver las categorias activas
    @GetMapping("/active")
    public ResponseEntity<List<Category>> findActive(){
	return svc.findActive();
    }

     // Agregamos el endpoint para crear categorias    
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody DtoCategoryIn in){
        return svc.create(in);
    }


}