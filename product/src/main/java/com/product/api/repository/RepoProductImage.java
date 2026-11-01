package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.entity.ProductImage;

@Repository
public interface RepoProductImage extends JpaRepository<ProductImage, Integer> {

    @Query(value = "Select * from product_image where product_image_id = :productImageId and product_id = :productId", nativeQuery = true)
    ProductImage findByProductImageAndProductId(@Param("productImageId") Integer productImageId,@Param("productId") Integer productId);

}
