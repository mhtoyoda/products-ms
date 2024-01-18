package com.toyoda.product.usecase.brand;

import com.toyoda.product.dto.brand.BrandDto;
import com.toyoda.product.entity.Brand;
import com.toyoda.product.exception.BrandNotFoundException;
import com.toyoda.product.service.product.ProductService;
import com.toyoda.product.usecase.brand.mapper.BrandMapper;
import com.toyoda.product.service.brand.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PutBrandUseCase {

    private final BrandService service;
    private final ProductService productService;
    private final BrandMapper mapper;

    public void execute(Integer id, BrandDto brandDto) {
        Brand brand = service.findBrandById(id).orElseThrow(() -> new BrandNotFoundException("Brand not found"));
        mapper.brandMapperByDto(brand, brandDto);
        service.updateBrand(brand);
        updateProduct(brand);
    }

    private void updateProduct(Brand brand) {
        boolean brandEnabled = brand.isEnabled();
        productService.findProductByBrand(brand.getId()).ifPresent(products -> {
            products.forEach(p -> p.setEnabled(brandEnabled));
            productService.updateProducts(products);
        });
    }
}
