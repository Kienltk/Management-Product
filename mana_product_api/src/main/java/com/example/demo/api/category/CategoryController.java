package com.example.demo.api.category;

import com.example.demo.api.brand.mapper.BrandMapper;
import com.example.demo.api.brand.request.BrandGetRequest;
import com.example.demo.helper.base.construct.RestFullRController;
import com.example.demo.helper.base.paginate.Pagination;
import com.example.demo.helper.base.response.ResponseObject;
import com.example.demo.api.category.mapper.CategoryMapper;
import com.example.demo.api.category.request.CategoryGetRequest;
import com.example.demo.api.category.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
@Validated
public class CategoryController extends RestFullRController<CategoryGetRequest> {
    public final CategoryService categoryService;

    @Override
    public ResponseObject<?> getPaginate(CategoryGetRequest categoryGetRequest) {
        return new Pagination<>(
                categoryService.getAll(categoryGetRequest),
                categoryGetRequest,
                new CategoryMapper()::toCategoryResponse,
                category -> Stream.of(
                        category.getCategoryName()
                )
        ).handlePaginateWithSearch();
    }

    @Override
    public ResponseObject<?> get(Integer id) {
        return new ResponseObject<>(HttpStatus.OK.toString(), categoryService.get(id));
    }
}
