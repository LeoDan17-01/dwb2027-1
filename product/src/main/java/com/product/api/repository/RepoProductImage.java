package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.dto.out.DtoProductOut;
import com.product.api.entity.ProductImage;

@Repository
public interface RepoProductImage extends JpaRepository<ProductImage, Integer> {

    List<ProductImage> findByProductId(Integer productId);

    List<ProductImage> findByProductIdAndStatus(Integer productId, Integer status);

    @Query(value = "Select * from product_image where product_image_id = :productImageId and product_id = :productId", nativeQuery = true)
    ProductImage findByProductImageAndProductId(@Param("productImageId") Integer productImageId,@Param("productId") Integer productId);

    @Query(value = "SELECT c.product_id, c.name, c.surname, c.rfc, c.mail, c.phone_number, c. address, r.region, ci.image "
    + "FROM product c "
    + "INNER JOIN region r ON r.region_id = c.region_id "
    + "INNER JOIN product_image ci ON ci.product_id = c.product_id "
        + "WHERE c.product_id = :product_id;", nativeQuery = true)
    DtoProductOut getProduct(@Param("product_id") Integer product_id);


}
