package com.product.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.dto.out.DtoProductImageOut;
import com.product.api.repository.RepoProductImage;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;
import com.product.api.entity.ProductImage;

public class SvcProductImageImp implements SvcProductImage {

    @Autowired
	RepoProductImage repo;

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Value("${app.upload.images}")
    private String uploadImages;

    @Override 
    public ResponseEntity<List<DtoProductImageOut>> getProductImages(Integer productId) {
        return null;
    }
    
    @Override 
    public ResponseEntity<String> createProductImage(Integer productId, DtoProductImageIn in) {
        try {
		    if(in.getImage().startsWith("data:image")){
                int commaIndex = in.getImage().indexOf(',');
                if (commaIndex != -1) {
                    in.setImage(in.getImage().substring(commaIndex + 1));
                }
            }
            // Decodifica la cadena Base64 a bytes
            byte[] imageBytes = Base64.getDecoder().decode(in.getImage());
            String fileName = UUID.randomUUID().toString() + ".png";
            Path imagePath = Paths.get(uploadDir, uploadImages, "product", fileName);

            // Asegurarse de que el directorio exista
            Files.createDirectories(imagePath.getParent());
            Files.write(imagePath, imageBytes);

            ProductImage productImage = new ProductImage();
            productImage.setProductImageId(in.getProductId());
            productImage.setImage("/product/" + fileName);
            productImage.setStatus(1); 

            repo.save(productImage);

            return new ResponseEntity<>("Imagen del producto creada correctamente", HttpStatus.CREATED);

        } catch (IOException e) {
            throw new ApiException( "Error al guardar el archivo",HttpStatus.INTERNAL_SERVER_ERROR);
        }


		catch (DataAccessException e) {
		    throw new DBAccessException(e);
		}
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
