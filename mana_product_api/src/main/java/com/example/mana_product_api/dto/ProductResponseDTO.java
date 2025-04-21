package com.example.mana_product_api.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProductResponseDTO {
    private Integer id;
    private String productName;
    private Float price;
    private String content;
    private String description;
    private String hrefParam;
    private CategoryDTO category;
    private BrandDTO brand;
    private List<PromotionalDTO> promotions;

    @Data
    public static class CategoryDTO {
        private Integer id;
        private String categoryName;
    }

    @Data
    public static class BrandDTO {
        private Integer id;
        private String brandName;
    }
}
