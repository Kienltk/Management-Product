package com.example.demo.api.brand.model;

import com.example.demo.helper.base.model.BaseModel;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "brand")
@Data
public class Brand extends BaseModel {

    @Column(name = "brand_name", nullable = false, length = 100)
    private String brandName;
}