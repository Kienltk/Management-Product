package com.example.mana_product_api.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProductRequestDTO {
    private Integer id;
    private String productName;
    private Float price;
    private String content;
    private String description;
    private String hrefParam;
    private Integer categoryId;
    private Integer brandId;
    private List<PromotionalDTO> promotions;
}