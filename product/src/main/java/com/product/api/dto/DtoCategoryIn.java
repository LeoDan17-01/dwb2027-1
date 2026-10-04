package com.product.api.dto;

import jakarta.validation.constraints.NotBlank;

public class DtoCategoryIn {
    // Se agrego @NotBlank para validar que los campos no sean nulos o vacios
    @NotBlank (message = "El nombre de la categoria no puede ser nulo")
    private String category;

    @NotBlank (message = "La etiqueta no puede ser nula")
    private String tag;
    
    private Integer parentCategoryId;

    // Constructor sin parametros
    public DtoCategoryIn() {
    }

    public DtoCategoryIn(String category, String tag, Integer parentCategoryId) {
        this.category = category;
        this.tag = tag;
        this.parentCategoryId = parentCategoryId;
    }
    
    // Getters y Setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

}
