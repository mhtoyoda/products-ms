package com.toyoda.product.usecase.category;

import com.toyoda.product.dto.category.CategoryDto;
import com.toyoda.product.usecase.category.mapper.CategoryMapper;
import com.toyoda.product.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetCategoriesUseCase {

    private final CategoryService service;
    private final CategoryMapper mapper;

    public List<CategoryDto> execute() {
        return service.listAll().stream().map(mapper::categoryMapper).collect(Collectors.toList());
    }
}
