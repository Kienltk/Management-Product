package com.example.demo.api.brand.service;

import com.example.demo.helper.base.construct.RestfullRService;
import com.example.demo.helper.base.repository.BaseRepository;
import com.example.demo.api.brand.model.Brand;
import com.example.demo.api.brand.repository.BrandRepository;
import com.example.demo.api.brand.request.BrandGetRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandService extends RestfullRService<Brand, BrandGetRequest> {
    private BaseRepository<Brand, Integer> baseRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.baseRepository = new BaseRepository<>(brandRepository, Brand.class);
    }

    @Override
    public List<Brand> getAll(BrandGetRequest brandGetRequest) {
        return this.baseRepository.getAll();
    }

    @Override
    public Brand get(Integer id) {
        return this.baseRepository.get(id);
    }
}
