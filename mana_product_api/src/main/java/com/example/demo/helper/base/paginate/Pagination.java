package com.example.demo.helper.base.paginate;


import com.example.demo.helper.base.constant.StatusMessage;
import com.example.demo.helper.base.paginate.dto.ResponsePaginate;
import com.example.demo.helper.base.request.PaginateParams;
import com.example.demo.helper.base.response.ResponseObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class Pagination<T, R> {
    private List<T> data;
    private final int PAGE = 1;
    private final int LIMIT = 10;
    private final boolean IS_PAGINATE = false;
    private Function<T, R> responseFunction;
    private PaginateParams paginateParams = new PaginateParams();
    private Function<T, Stream<String>> searchableFieldsGetter;

    public Pagination(List<T> data, R request, Function<T, R> responseFunction, Function<T, Stream<String>> searchableFieldsGetter) {
        this.data = data != null ? data : new ArrayList<>();
        if (request != null) this.validRequestParams(request);
        this.responseFunction = responseFunction;
        this.searchableFieldsGetter = searchableFieldsGetter;
    }

    private void validRequestParams(R request) {
        BeanUtils.copyProperties(request, this.paginateParams);
        this.paginateParams.setPage(this.paginateParams.getPage() > 0 ? this.paginateParams.getPage() : PAGE);
        this.paginateParams.setLimit(this.paginateParams.getLimit() > 0 ? this.paginateParams.getLimit() : LIMIT);

        if (this.paginateParams.getIsPaginate() == null) {
            this.paginateParams.setIsPaginate(IS_PAGINATE);
        }
    }

    private List<R> toMapperResponse(List<T> dataPage) {
        return dataPage.stream().map(responseFunction).collect(Collectors.toList());
    }

    public ResponseObject<?> handlePaginateWithSearch() {
        List<T> filteredData = this.data;

        // Nếu có tìm kiếm, lọc dữ liệu theo từ khóa
        if (paginateParams.getSearch() != null && !paginateParams.getSearch().isEmpty()) {
            String keyword = paginateParams.getSearch().toLowerCase();
            filteredData = this.data.stream()
                    .filter(item -> {
                        Stream<String> searchableFields = searchableFieldsGetter.apply(item);

                        return searchableFields != null && searchableFields.anyMatch(field ->
                                field != null && field.toLowerCase().contains(keyword)
                        );
                    })
                    .collect(Collectors.toList());
        }

        if (!this.paginateParams.getIsPaginate()) {
            return new ResponseObject<>(StatusMessage.SUCCESS, toMapperResponse(filteredData));
        }

        int totalItems = filteredData.size();
        int totalPage = (int) Math.ceil((float) totalItems / this.paginateParams.getLimit());

        List<T> dataPage = new ArrayList<>();

        if (this.paginateParams.getPage() <= totalPage && totalItems > 0) {
            int offset = (this.paginateParams.getPage() - 1) * this.paginateParams.getLimit();
            int endIndex = Math.min(offset + this.paginateParams.getLimit(), totalItems);
            dataPage = filteredData.subList(offset, endIndex);
        }

        return new ResponseObject<>(StatusMessage.SUCCESS,
                new ResponsePaginate<>(
                        this.paginateParams.getPage(),
                        this.paginateParams.getLimit(),
                        totalItems,
                        totalPage,
                        toMapperResponse(dataPage)
                ));
    }

}
