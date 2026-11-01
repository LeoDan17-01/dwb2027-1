package com.product.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.dto.out.DtoProductImageOut;
import com.product.api.service.SvcProductImage;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("product/{id}/image")
public class CtrlProductImage {
    
    @Autowired 
    SvcProductImage svc;

    @GetMapping 
    public ResponseEntity<List<DtoProductImageOut>> getProductImages(@PathVariable("id") Integer productId) {
        return svc.getProductImages(productId);
    }

    @PostMapping 
    public ResponseEntity<String> createProductImage(@PathVariable("id") Integer productId, @Valid @RequestBody DtoProductImageIn in) {
        return svc.createProductImage(productId, in);
    }

    @DeleteMapping("/{productImageId}")
    public ResponseEntity<String> deleteProductImage(@PathVariable("id") Integer productId, @PathVariable("productImageId") Integer productImageId) {
        return svc.deleteProductImage(productId, productImageId);
    }

}
