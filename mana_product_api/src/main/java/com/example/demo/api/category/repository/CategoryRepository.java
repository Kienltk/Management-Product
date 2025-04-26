package com.example.demo.api.category.repository;

import com.example.demo.api.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findById(Integer categoryId);
}
