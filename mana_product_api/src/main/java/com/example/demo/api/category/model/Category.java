package com.example.demo.api.category.model;

import com.example.demo.helper.base.model.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseModel {
    @Column(name = "category_name", nullable = false, length = 50)
    private String categoryName;
}
