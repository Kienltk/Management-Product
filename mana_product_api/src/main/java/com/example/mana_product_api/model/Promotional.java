package com.example.mana_product_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "promotional")
@Data
public class Promotional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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