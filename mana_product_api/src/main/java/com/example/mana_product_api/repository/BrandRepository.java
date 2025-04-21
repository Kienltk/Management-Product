package com.example.mana_product_api.repository;

import com.example.mana_product_api.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
