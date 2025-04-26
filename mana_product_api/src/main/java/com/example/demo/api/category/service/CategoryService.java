package com.example.demo.api.category.service;

import com.example.demo.helper.base.construct.RestfullRService;
import com.example.demo.helper.base.repository.BaseRepository;
import com.example.demo.api.category.model.Category;
import com.example.demo.api.category.repository.CategoryRepository;
import com.example.demo.api.category.request.CategoryGetRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService extends RestfullRService<Category, CategoryGetRequest> {
    private BaseRepository<Category, Integer> baseRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.baseRepository=new BaseRepository<>(categoryRepository,Category.class);
    }
    @Override
    public List<Category> getAll(CategoryGetRequest categoryGetRequest) {
        return this.baseRepository.getAll();
    }

    @Override
    public Category get(Integer id) {
        return this.baseRepository.get(id);
    }
}
