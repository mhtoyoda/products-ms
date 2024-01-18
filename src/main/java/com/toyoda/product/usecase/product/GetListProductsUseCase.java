package com.toyoda.product.usecase.product;

import com.toyoda.product.dto.product.ProductDto;
import com.toyoda.product.entity.Product;
import com.toyoda.product.service.product.ProductService;
import com.toyoda.product.usecase.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetListProductsUseCase {

    private final ProductService service;
    private final ProductMapper mapper;

    public List<ProductDto> execute() {
        return service.listProducts().stream().filter(Product::isEnabled).map(mapper::productMapper).collect(Collectors.toList());
    }
}
