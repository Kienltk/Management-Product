package com.example.demo.api.product;

import com.example.demo.api.product.model.Product;
import com.example.demo.helper.base.constant.StatusMessage;
import com.example.demo.helper.base.construct.RestfullController;
import com.example.demo.helper.base.paginate.Pagination;
import com.example.demo.helper.base.response.ResponseObject;
import com.example.demo.api.product.mapper.ProductMapper;
import com.example.demo.api.product.request.ProductGetRequest;
import com.example.demo.api.product.request.ProductStoreRequest;
import com.example.demo.api.product.request.ProductUpdateRequest;
import com.example.demo.api.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;


@RequestMapping("/api/products")
@AllArgsConstructor
@Validated
@RestController
public class ProductController extends RestfullController<ProductGetRequest, ProductStoreRequest, ProductUpdateRequest> {
    public final ProductService productService;

    @Override
    public ResponseObject<?> getPaginate(ProductGetRequest request) {
        return new Pagination<>(
                productService.getAll(request),
                request,
                new ProductMapper()::toProductResponse,
                product -> Stream.of(
                        product.getProductName(),
                        product.getContent(),
                        product.getDescription(),
                        product.getCategory() != null ? product.getCategory().getCategoryName() : null,
                        product.getBrand() != null ? product.getBrand().getBrandName() : null
                )
        ).handlePaginateWithSearch();
    }


    @Override
    public ResponseObject<?> get(@PathVariable Integer id) {
        return new ResponseObject<>(StatusMessage.SUCCESS, new ProductMapper().toProductResponse(productService.get(id)));
    }

    @Override
    public ResponseObject<?> store(@RequestBody @Validated ProductStoreRequest productStoreRequest) {
        return new ResponseObject<>(StatusMessage.SUCCESS, new ProductMapper().toProductResponse(productService.store(productStoreRequest)));
    }

    @Override
    public ResponseObject<?> update(@PathVariable Integer id, @RequestBody @Validated ProductUpdateRequest productUpdateRequest) {
        return new ResponseObject<>(StatusMessage.SUCCESS, new ProductMapper().toProductResponse(productService.update(id, productUpdateRequest)));
    }

    @Override
    public ResponseObject<?> destroy(@PathVariable Integer id) {
        return new ResponseObject<>(StatusMessage.SUCCESS, new ProductMapper().toProductResponse(productService.destroy(id)));
    }
}
