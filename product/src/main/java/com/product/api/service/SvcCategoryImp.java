package com.product.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

import jakarta.transaction.Transactional;

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
        }catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }    

    // Agregamos el metodo para ver las categorias activas
    @Override
    public ResponseEntity<List<Category>> findActive() {
        try {
            return new ResponseEntity<>(repo.findActive(), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    } 

    @Override 
    public ResponseEntity<List<Category>> findChilds(Integer id){
        try{
            return new ResponseEntity<>(repo.findChilds(id), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }
    
    // Agregamos el metodo para crear categorias
    @Transactional 
    public ResponseEntity<Void> create(DtoCategoryIn in){
        try{
            Category category = new Category();
            category.setCategory(in.getCategory());
            category.setTag(in.getTag());
            category.setParentCategoryId(in.getParentCategoryId());
            category.setStatus(1); 

            repo.save(category);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataAccessException e){
            throw new DBAccessException(e);
        }
    }
}