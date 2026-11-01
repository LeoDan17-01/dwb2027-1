package com.product.api.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DtoProductOut {

    @JsonProperty("product_id")
    private Integer product_id;

    @JsonProperty("gtin")
    private String gtin;

    @JsonProperty("product")
    private String product;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Float price;

    @JsonProperty("stock")
    private Integer stock;

    @JsonProperty("category_id")
    private Integer category_id;

    @JsonProperty("status")
    private Integer status;
}
