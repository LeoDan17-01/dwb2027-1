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
    public ResponseEntity<List<Category>> getCategories(){
        try {
            return new ResponseEntity<>(repo.getCategories(), HttpStatus.OK);
        }catch (Exception e){
            throw new ApiException("Error al obtener las categorias", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }    
}