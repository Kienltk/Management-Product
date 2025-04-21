package com.example.mana_product_api.service;

import com.example.mana_product_api.dto.*;
import com.example.mana_product_api.model.*;
import com.example.mana_product_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponseDTO> getProductById(Integer id) {
        return productRepository.findById(id)
                .map(this::convertToResponseDTO);
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToResponseDTO(savedProduct);
    }

    public Optional<ProductResponseDTO> updateProduct(Integer id, ProductRequestDTO productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setProductName(productDetails.getProductName());
            product.setPrice(productDetails.getPrice());
            product.setContent(productDetails.getContent());
            product.setDescription(productDetails.getDescription());
            product.setHrefParam(productDetails.getHrefParam());
            if (productDetails.getCategoryId() != null) {
                product.setCategory(categoryRepository.findById(productDetails.getCategoryId()).orElse(null));
            }
            if (productDetails.getBrandId() != null) {
                product.setBrand(brandRepository.findById(productDetails.getBrandId()).orElse(null));
            }
            if (productDetails.getPromotions() != null) {
                product.getPromotions().clear();
                productDetails.getPromotions().forEach(promoDto -> {
                    Promotional promo = new Promotional();
                    promo.setPromotional(promoDto.getPromotional());
                    promo.setPromotionalStart(promoDto.getPromotionalStart());
                    promo.setPromotionalEnd(promoDto.getPromotionalEnd());
                    promo.setProduct(product);
                    product.getPromotions().add(promo);
                });
            }
            Product updatedProduct = productRepository.save(product);
            return convertToResponseDTO(updatedProduct);
        });
    }

    public boolean deleteProduct(Integer id) {
        return productRepository.findById(id).map(product -> {
            productRepository.delete(product);
            return true;
        }).orElse(false);
    }

    private ProductResponseDTO convertToResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setPrice(product.getPrice());
        dto.setContent(product.getContent());
        dto.setDescription(product.getDescription());
        dto.setHrefParam(product.getHrefParam());
        if (product.getCategory() != null) {
            ProductResponseDTO.CategoryDTO categoryDTO = new ProductResponseDTO.CategoryDTO();
            categoryDTO.setId(product.getCategory().getId());
            categoryDTO.setCategoryName(product.getCategory().getCategoryName());
            dto.setCategory(categoryDTO);
        }
        if (product.getBrand() != null) {
            ProductResponseDTO.BrandDTO brandDTO = new ProductResponseDTO.BrandDTO();
            brandDTO.setId(product.getBrand().getId());
            brandDTO.setBrandName(product.getBrand().getBrandName());
            dto.setBrand(brandDTO);
        }
        if (product.getPromotions() != null) {
            dto.setPromotions(product.getPromotions().stream()
                    .map(this::convertToPromotionalDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    private PromotionalDTO convertToPromotionalDTO(Promotional promotional) {
        PromotionalDTO dto = new PromotionalDTO();
        dto.setId(promotional.getId());
        dto.setPromotional(promotional.getPromotional());
        dto.setPromotionalStart(promotional.getPromotionalStart());
        dto.setPromotionalEnd(promotional.getPromotionalEnd());
        return dto;
    }

    private Product convertToEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setContent(dto.getContent());
        product.setDescription(dto.getDescription());
        product.setHrefParam(dto.getHrefParam());
        if (dto.getCategoryId() != null) {
            product.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));
        }
        if (dto.getBrandId() != null) {
            product.setBrand(brandRepository.findById(dto.getBrandId()).orElse(null));
        }
        if (dto.getPromotions() != null) {
            List<Promotional> promotions = dto.getPromotions().stream()
                    .map(promoDto -> {
                        Promotional promo = new Promotional();
                        promo.setPromotional(promoDto.getPromotional());
                        promo.setPromotionalStart(promoDto.getPromotionalStart());
                        promo.setPromotionalEnd(promoDto.getPromotionalEnd());
                        promo.setProduct(product);
                        return promo;
                    }).collect(Collectors.toList());
            product.setPromotions(promotions);
        }
        return product;
    }
}