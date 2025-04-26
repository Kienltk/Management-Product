package com.example.demo.helper.base.paginate.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponsePaginate<T> {
    public int page;
    public int limit;
    public long totalItems;
    public int totalPage;
    public List<T> data;

    public ResponsePaginate(int page, int limit, long totalItems, int totalPage, List<T> data) {
        this.page = page;
        this.limit = limit;
        this.totalItems = totalItems;
        this.totalPage = totalPage;
        this.data = data;
    }
}