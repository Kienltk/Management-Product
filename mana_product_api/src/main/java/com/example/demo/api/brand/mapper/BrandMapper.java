package com.example.demo.api.brand.mapper;

import com.example.demo.api.brand.model.Brand;
import com.example.demo.api.brand.dto.BrandResponse;

public class BrandMapper {
    public BrandResponse toBrandResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .brandName(brand.getBrandName())
                .build();
    }
}
