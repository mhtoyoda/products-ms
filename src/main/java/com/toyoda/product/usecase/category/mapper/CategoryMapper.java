package com.toyoda.product.usecase.category.mapper;

import com.toyoda.product.dto.category.CategoryDto;
import com.toyoda.product.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto categoryMapper(Category category);

    @Mapping(target = "id", ignore = true)
    void categoryMapperByDto(@MappingTarget Category category, CategoryDto categoryDto);
}
