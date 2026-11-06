package com.product.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.api.dto.in.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Objects;

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
        try {
            validateRegionId(id);
            return new ResponseEntity<>(repo.findChilds(id), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }
    
    // Agregamos el metodo para crear categorias
    @Transactional 
    public ResponseEntity<Void> create(DtoCategoryIn in){
        try{

            if (repo.existsByCategory(in.getCategory()))
                throw new ApiException("El nombre de la categoría ya está registrado", HttpStatus.CONFLICT);

            if (repo.existsByTag(in.getTag()))
                throw new ApiException("El tag de la categoría ya está registrado", HttpStatus.CONFLICT);

            if (in.getParentCategoryId() != null) {
                repo.findActiveById(in.getParentCategoryId())
                    .orElseThrow(() -> new ApiException(
                        "La categoría padre no existe o no está activa", 
                        HttpStatus.NOT_FOUND));
            }
            repo.create(in.getCategory(), in.getTag(), in.getParentCategoryId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataAccessException e){
            if (e.getLocalizedMessage().contains("ux_region"))
                throw new ApiException("El nombre de la región ya está registrado", HttpStatus.CONFLICT);
            if (e.getLocalizedMessage().contains("ux_tag"))
                throw new ApiException("El tag de la región ya está registrado", HttpStatus.CONFLICT);
            throw new DBAccessException(e);
        }
    }

    //Agreamos el metodo para actualizar categorias
    @Transactional
    public ResponseEntity<Void> update(DtoCategoryIn in, Integer id){
        try{
            validateRegionId(id);

            Category actual = repo.findById(id).orElseThrow(() ->
                new ApiException("El id de la categoría no existe", HttpStatus.NOT_FOUND));

            if (!Objects.equals(actual.getCategory(), in.getCategory())
                    && repo.existsByCategory(in.getCategory()))
                throw new ApiException("El nombre de la categoría ya está registrado", HttpStatus.CONFLICT);

            if (!Objects.equals(actual.getTag(), in.getTag())
                    && repo.existsByTag(in.getTag()))
                throw new ApiException("El tag de la categoría ya está registrado", HttpStatus.CONFLICT);

            if (in.getParentCategoryId() != null && in.getParentCategoryId().equals(id))
                throw new ApiException("Una categoría no puede ser padre de sí misma", HttpStatus.CONFLICT);

            if (in.getParentCategoryId() != null) {
                repo.findActiveById(in.getParentCategoryId())
                    .orElseThrow(() -> new ApiException(
                        "La categoría padre no existe o no está activa",
                        HttpStatus.NOT_FOUND));
            }

            repo.update(id, in.getCategory(), in.getTag(), in.getParentCategoryId());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (DataAccessException e){
            throw new DBAccessException(e);
        }
    }

    // Metodo auxiliar para validar que la categoria existe
    private void validateRegionId(Integer id) {
        if (repo.findById(id).isEmpty()) {
            throw new ApiException("El id de la región no existe", HttpStatus.NOT_FOUND);
        }
    }

    // Agregamos el metodo para cambiar el estatus de una categoria
    @Transactional
    public ResponseEntity<Void> enable(Integer id){
        try{
            // Llamamos al metodo auxiliar para validar que la categoria existe
            validateRegionId(id);
            repo.updateStatus(id, 1);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (DataAccessException e){
            throw new DBAccessException(e);
        }
    }

    @Transactional
    public ResponseEntity<Void> disable(Integer id){
        try{
            validateRegionId(id);
            if (repo.hasChildren(id))
                throw new ApiException(
                    "No es posible deshabilitar una categoría que tiene categorías hijas",
                    HttpStatus.CONFLICT);
            repo.updateStatus(id, 0);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (DataAccessException e){
            throw new DBAccessException(e);
        }
    }

}