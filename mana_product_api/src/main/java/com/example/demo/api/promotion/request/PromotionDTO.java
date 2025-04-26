package com.example.demo.api.promotion.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class PromotionDTO {
    private Integer id;
    private Integer promotional;
    private LocalDateTime promotionalStart;
    private LocalDateTime promotionalEnd;
}
