package com.product.common.mapper;

import java.util.List;

import com.product.api.dto.in.DtoProductIn;
import com.product.api.entity.Product;

public class MapperProduct {
    
    public Product fromDto(Integer id, DtoProductIn dto) {
        Product product = fromDto(dto);
        product.setProduct_id(id);
        return product;
    }

    public List<Product> fromProductList(List<Product> products) {
        return products;
    }
    
}
