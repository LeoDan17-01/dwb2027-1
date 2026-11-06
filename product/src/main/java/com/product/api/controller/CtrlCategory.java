package com.product.api.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;
import com.product.api.dto.in.DtoCategoryIn;

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

     // Agregamos los endpoints para implementar el CRUD  
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody DtoCategoryIn in){
        return svc.create(in);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody DtoCategoryIn in, @PathVariable Integer id){
        return svc.update(in, id);
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<Void> enable(@PathVariable Integer id) {
        return svc.enable(id);
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<Void> disable(@PathVariable Integer id) {
        return svc.disable(id);
    }

    @GetMapping("/{id}/childs")
    public ResponseEntity<List<Category>> findChilds(@PathVariable Integer id) {
        return svc.findChilds(id);
    }

}