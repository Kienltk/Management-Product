package com.example.demo.api.brand;

import com.example.demo.helper.base.construct.RestFullRController;
import com.example.demo.helper.base.paginate.Pagination;
import com.example.demo.helper.base.response.ResponseObject;
import com.example.demo.api.brand.mapper.BrandMapper;
import com.example.demo.api.brand.request.BrandGetRequest;
import com.example.demo.api.brand.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
@Validated
public class BrandController extends RestFullRController<BrandGetRequest> {
    public final BrandService brandService;
    @Override
    public ResponseObject<?> getPaginate(BrandGetRequest brandGetRequest) {
        return new Pagination<>(
                brandService.getAll(brandGetRequest),
                brandGetRequest,
                new BrandMapper()::toBrandResponse,
                brand -> Stream.of(
                        brand.getBrandName()
                )
        ).handlePaginateWithSearch();
    }

    @Override
    public ResponseObject<?> get(Integer id) {
        return new ResponseObject<>(HttpStatus.OK.toString(), brandService.get(id));
    }
}
