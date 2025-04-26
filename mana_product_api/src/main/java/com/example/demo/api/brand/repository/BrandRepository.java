package com.example.demo.api.brand.repository;

import com.example.demo.api.brand.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
    Optional<Brand> findById(Integer brandId);
}
