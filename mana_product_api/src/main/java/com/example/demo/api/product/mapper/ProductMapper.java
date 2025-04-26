package com.example.demo.api.product.mapper;

import com.example.demo.api.product.model.Product;
import com.example.demo.api.promotion.model.Promotional;
import com.example.demo.api.promotion.request.PromotionDTO;
import com.example.demo.api.product.dto.ProductResponse;
import com.example.demo.utils.formater.money.MoneyFormater;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    private final MoneyFormater moneyFormater = new MoneyFormater();
    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .brandId(product.getBrand().getId())
                .categoryId(product.getCategory().getId())
                .content(product.getContent())
                .description(product.getDescription())
                .hrefParam(product.getHrefParam())
                .productName(product.getProductName())
                .promotions(mapPromotions(product.getPromotions()))
                .price(product.getPrice())
                .build();
    }

    private List<PromotionDTO> mapPromotions(List<Promotional> promotions) {
        if (promotions == null || promotions.isEmpty()) {
            return new ArrayList<>();
        }
        return promotions.stream().map(p -> {
            PromotionDTO dto = new PromotionDTO();
            dto.setId(p.getId());
            dto.setPromotional(p.getPromotional());
            dto.setPromotionalStart(p.getPromotionalStart());
            dto.setPromotionalEnd(p.getPromotionalEnd());
            return dto;
        }).collect(Collectors.toList());
    }

}
