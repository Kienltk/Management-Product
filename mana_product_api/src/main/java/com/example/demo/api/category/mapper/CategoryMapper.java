package com.example.demo.api.category.mapper;

import com.example.demo.api.category.model.Category;
import com.example.demo.api.category.dto.CategoryResponse;

public class CategoryMapper {
    public CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }
}
