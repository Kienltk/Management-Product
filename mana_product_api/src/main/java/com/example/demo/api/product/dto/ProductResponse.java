package com.example.demo.api.product.dto;

import com.example.demo.api.promotion.request.PromotionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Integer id;
    private String productName;
    private Float price;
    private String content;
    private Integer categoryId;
    private Integer brandId;
    private String description;
    private String hrefParam;
    private List<PromotionDTO> promotions;
}
