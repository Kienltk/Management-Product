package com.example.demo.api.product.service;

import com.example.demo.helper.base.construct.RestfullService;
import com.example.demo.helper.base.repository.BaseRepository;
import com.example.demo.api.brand.model.Brand;
import com.example.demo.api.category.model.Category;
import com.example.demo.api.product.model.Product;
import com.example.demo.api.promotion.model.Promotional;
import com.example.demo.api.brand.repository.BrandRepository;
import com.example.demo.api.category.repository.CategoryRepository;
import com.example.demo.api.product.repository.ProductRepository;
import com.example.demo.api.product.request.ProductGetRequest;
import com.example.demo.api.product.request.ProductStoreRequest;
import com.example.demo.api.product.request.ProductUpdateRequest;
import com.example.demo.api.promotion.request.PromotionDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService extends RestfullService<Product, ProductGetRequest, ProductStoreRequest, ProductUpdateRequest> {
    private BaseRepository<Product,Integer> baseRepository;
    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, BrandRepository brandRepository,CategoryRepository categoryRepository) {
        this.baseRepository = new BaseRepository<>(productRepository, Product.class);
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Product> getAll(ProductGetRequest productGetRequest) {
        return this.baseRepository.getAll();
    }

    @Override
    public Product get(Integer id) {
        return this.baseRepository.get(id);
    }

    @Override
    public Product store(ProductStoreRequest productStoreRequest) {
        Category category = findCategory(productStoreRequest.getCategoryId());
        Brand brand = findBrand(productStoreRequest.getBrandId());

        Product product = Product.builder()
                .brand(brand)
                .category(category)
                .price(productStoreRequest.getPrice())
                .productName(productStoreRequest.getProductName())
                .hrefParam(productStoreRequest.getHrefParam())
                .content(productStoreRequest.getContent())
                .description(productStoreRequest.getDescription())
                .build();

        List<PromotionDTO> newPromotions = productStoreRequest.getPromotions();
        if (newPromotions != null && !newPromotions.isEmpty()) {
            product.setPromotions(buildPromotions(newPromotions, product));
        }

        return baseRepository.save(product);
    }


    @Override
    public Product update(Integer id, ProductUpdateRequest productUpdateRequest) {
        Product existingProduct = baseRepository.get(id);
        if (existingProduct == null) throw new RuntimeException("Product not found");

        Category category = findCategory(productUpdateRequest.getCategoryId());
        Brand brand = findBrand(productUpdateRequest.getBrandId());

        existingProduct.setBrand(brand);
        existingProduct.setCategory(category);
        existingProduct.setPrice(productUpdateRequest.getPrice());
        existingProduct.setProductName(productUpdateRequest.getProductName());
        existingProduct.setHrefParam(productUpdateRequest.getHrefParam());
        existingProduct.setContent(productUpdateRequest.getContent());
        existingProduct.setDescription(productUpdateRequest.getDescription());

        List<PromotionDTO> updatedPromotions = productUpdateRequest.getPromotions();
        if (updatedPromotions != null) {
            if (existingProduct.getPromotions() != null) {
                existingProduct.getPromotions().clear();
            }

            List<Promotional> newPromotions = buildPromotions(updatedPromotions, existingProduct);
            if (newPromotions != null) {
                existingProduct.getPromotions().addAll(newPromotions);
            }
        }

        return baseRepository.save(existingProduct);
    }

    @Override
    public Product destroy(Integer id) {
        return this.baseRepository.delete(id);
    }
    private Category findCategory(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    private Brand findBrand(Integer id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
    }

    private List<Promotional> buildPromotions(List<PromotionDTO> dtos, Product product) {
        if (dtos == null || dtos.isEmpty()) return null;

        return dtos.stream().map(dto ->
                Promotional.builder()
                        .promotional(dto.getPromotional())
                        .promotionalStart(dto.getPromotionalStart())
                        .promotionalEnd(dto.getPromotionalEnd())
                        .product(product)
                        .build()
        ).collect(Collectors.toList());
    }
}
