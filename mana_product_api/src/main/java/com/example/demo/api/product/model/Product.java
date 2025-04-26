package com.example.demo.api.product.model;

import com.example.demo.api.brand.model.Brand;
import com.example.demo.api.category.model.Category;
import com.example.demo.helper.base.model.BaseModel;
import com.example.demo.api.promotion.model.Promotional;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {


    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "href_param", nullable = false, length = 255)
    private String hrefParam;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Promotional> promotions;
}