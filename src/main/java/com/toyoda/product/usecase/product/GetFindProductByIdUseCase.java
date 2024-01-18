package com.toyoda.product.usecase.product;

import com.toyoda.product.dto.product.ProductDto;
import com.toyoda.product.entity.Product;
import com.toyoda.product.exception.ProductNotFoundException;
import com.toyoda.product.service.product.ProductService;
import com.toyoda.product.usecase.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetFindProductByIdUseCase {

    private final ProductService service;
    private final ProductMapper mapper;

    public ProductDto execute(Integer id) {
        Product product = service.findProductById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return mapper.productMapper(product);
    }
}
