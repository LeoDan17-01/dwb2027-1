package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.api.dto.out.DtoProductImageOut;
import com.product.api.repository.RepoProductImage;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;
import com.product.api.entity.ProductImage;

public class SvcProductImageImp implements SvcProductImage {

    @Autowired
	RepoProductImage repo;

    @Override 
    public ResponseEntity<List<DtoProductImageOut>> getProductImages(Integer productId) {
        return null;
    }
    
    @Override 
    public ResponseEntity<String> createProductImage(Integer productId, Object in) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteProductImage(Integer productId, Integer productImageId) {
        try {
            validateProductId(productId);

            ProductImage productImage = repo.findByProductImageAndProductId(productImageId, productId);
            if (productImage == null) {
                throw new ApiException("La imagen del producto no existe", HttpStatus.NOT_FOUND);
            }

            productImage.setStatus(0);
            repo.save(productImage);

            return new ResponseEntity<>("La imagen ha sido eliminada", HttpStatus.OK);
        }catch (DataAccessException e) {

            throw new ApiException("Error al eliminar la imagen del producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateProductId(Integer id) {
		try {
			if(repo.findById(id).isEmpty()) {
				throw new ApiException("El id del producto no existe", HttpStatus.NOT_FOUND);
			}
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
}
