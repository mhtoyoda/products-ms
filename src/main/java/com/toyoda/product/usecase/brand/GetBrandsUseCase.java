package com.toyoda.product.usecase.brand;

import com.toyoda.product.dto.brand.BrandDto;
import com.toyoda.product.usecase.brand.mapper.BrandMapper;
import com.toyoda.product.service.brand.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetBrandsUseCase {

    private final BrandService service;
    private final BrandMapper mapper;

    public List<BrandDto> execute() {
        return service.listAll().stream().map(mapper::brandMapper).collect(Collectors.toList());
    }
}
