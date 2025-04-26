package com.example.demo.helper.base.request;


import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginateParams {
    @NotNull
    @Min(1)
    private int page = 1;

    @NotNull
    @Min(5)
    private int limit = 10;

    private String search;

    private Boolean isPaginate = false;
}