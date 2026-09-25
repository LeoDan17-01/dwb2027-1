package com.product;

public class Category {
    private Integer category_id;
    private String category;
    private String tag;
    private Integer parentCategoryId;
    private Integer status;

    public Category() {
    }

    // Constructor
    public Category(Integer category_id, String category, String tag,
                    Integer parentCategoryId, Integer status) {
        this.category_id = category_id;
        this.category = category;
        this.tag = tag;
        this.parentCategoryId = parentCategoryId;
        this.status = status;
    }

    // Getters y Setters
    public Integer getCategory_id() {
        return category_id; 
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    // ToString 
    @Override
    public String toString() {
        return "{" + category_id + ", \"" + category + "\", \"" + tag + "\", "
                + parentCategoryId + ", " + status + "}";
    }
}