package com.example.demo.api.product.request;

import com.example.demo.api.promotion.request.PromotionDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductUpdateRequest {
    private String productName;
    private Float price;
    private String content;
    private String description;
    private String hrefParam;
    private Integer categoryId;
    private Integer brandId;
    private List<PromotionDTO> promotions;
}
