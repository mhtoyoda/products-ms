package com.toyoda.product.usecase.category;

import com.toyoda.product.dto.category.CategoryDto;
import com.toyoda.product.entity.Brand;
import com.toyoda.product.entity.Category;
import com.toyoda.product.exception.CategoryNotFoundException;
import com.toyoda.product.service.product.ProductService;
import com.toyoda.product.usecase.category.mapper.CategoryMapper;
import com.toyoda.product.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PutCategoryUseCase {

    private final CategoryService service;
    private final ProductService productService;
    private final CategoryMapper mapper;

    public void execute(Integer id, CategoryDto categoryDto) {
        Category category = service.findCategoryById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        mapper.categoryMapperByDto(category, categoryDto);
        service.updateCategory(category);
        updateProduct(category);
    }

    private void updateProduct(Category category) {
        boolean categoryEnabled = category.isEnabled();
        productService.findProductByCategory(category.getId()).ifPresent(products -> {
            products.forEach(p -> p.setEnabled(categoryEnabled));
            productService.updateProducts(products);
        });
    }
}
