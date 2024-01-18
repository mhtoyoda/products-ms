package com.toyoda.product.usecase.product;

import com.toyoda.product.dto.product.ProductCreateDto;
import com.toyoda.product.entity.Brand;
import com.toyoda.product.entity.Category;
import com.toyoda.product.entity.Product;
import com.toyoda.product.exception.BrandNotFoundException;
import com.toyoda.product.exception.CategoryNotFoundException;
import com.toyoda.product.service.brand.BrandService;
import com.toyoda.product.service.category.CategoryService;
import com.toyoda.product.service.product.ProductService;
import com.toyoda.product.usecase.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostCreateProductUseCase {

    private final ProductService service;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final ProductMapper productMapper;

    public void execute(ProductCreateDto productDto) {
        Category category = validateCategory(productDto);
        Brand brand = validateBrand(productDto);
        Product product = productMapper.productMapper(productDto, category, brand);
        service.createProduct(product);
    }

    private Brand validateBrand(ProductCreateDto productDto) {
        return brandService.findBrandById(productDto.getBrandId())
                .orElseThrow(() -> new BrandNotFoundException("Brand not found"));
    }

    private Category validateCategory(ProductCreateDto productDto) {
        return categoryService.findCategoryById(productDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }
}
