package com.toyoda.product.usecase.product;

import com.toyoda.product.dto.product.ProductDto;
import com.toyoda.product.dto.product.ProductUpdateDto;
import com.toyoda.product.entity.Product;
import com.toyoda.product.exception.ProductNotFoundException;
import com.toyoda.product.service.product.ProductService;
import com.toyoda.product.usecase.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PutUpdateProductUseCase {

    private final ProductService service;
    private final ProductMapper mapper;

    public void execute(Integer id, ProductUpdateDto productDto) {
        Product product = service.findProductById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        mapper.productMapperByDto(product, productDto);
        service.updateProduct(product);
    }
}
