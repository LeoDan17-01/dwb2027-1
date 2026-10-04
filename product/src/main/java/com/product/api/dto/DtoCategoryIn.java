package com.product.api.dto;

public class DtoCategoryIn {

    private String category;
    private String tag;
    private Integer parentCategoryId;

    // Constructor sin parametros
    public DtoCategoryIn() {
    }

    // Constructor con parametros verificando que tag, category y parentCategoryId no sean nulos
    public DtoCategoryIn(String category, String tag, Integer parentCategoryId) {
        if (category == null || tag == null || parentCategoryId == null) {
            throw new IllegalArgumentException("Los campos category, tag y parentCategoryId no pueden ser nulos");
        }
        this.category = category;
        this.tag = tag;
        this.parentCategoryId = parentCategoryId;
    }
    
}
