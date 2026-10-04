package com.product.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.ApiException;

import java.util.List;

@Service
public class SvcCategoryImp implements SvcCategory {

    @Autowired
    RepoCategory repo;

    // Actualizamos el metodo con ResponseEntity y lanzamos ApiException en caso de error
    @Override
    public ResponseEntity<List<Category>> findAll(){
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            throw new ApiException("Error al obtener las categorias", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    

    // Agregamos el metodo para ver las categorias activas
    @Override
    public ResponseEntity<List<Category>> findActive() {
        try {
            return new ResponseEntity<>(repo.findActive(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ApiException("Error al consultar categorias activas", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
    
}