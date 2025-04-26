package com.example.demo.api.promotion.model;

import com.example.demo.api.product.model.Product;
import com.example.demo.helper.base.model.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "promotional")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Promotional extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "promotional", nullable = false)
    private Integer promotional;

    @Column(name = "promotional_start")
    private LocalDateTime promotionalStart;

    @Column(name = "promotional_end")
    private LocalDateTime promotionalEnd;
}