package com.product.common.mapper;

import com.product.api.dto.in.DtoProductIn;
import com.product.api.entity.Product;

public class MapperProduct {
    
    public Product fromDto(Integer id, DtoProductIn dto) {
        Product product = fromDto(dto);
        product.setProduct_id(id);
        return product;
    }

    public Product fromProductList(DtoProductIn dto) {
        Product product = new Product();
        product.setProduct(dto.getProduct());
        product.setGtin(dto.getGtin());
        return product;
    }
    
}
