package com.example.mana_product_api.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PromotionalDTO {
    private Integer id;
    private Integer promotional;
    private LocalDateTime promotionalStart;
    private LocalDateTime promotionalEnd;
}
