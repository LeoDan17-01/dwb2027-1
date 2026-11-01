package com.product.api.service;

import java.util.List;

import com.product.api.dto.out.DtoProductImageOut;

public interface SvcProductImage {
    
    List<DtoProductImageOut> getProductImages(Integer productId);

    
}
