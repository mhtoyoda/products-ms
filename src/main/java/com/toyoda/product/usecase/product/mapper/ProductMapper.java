package com.toyoda.product.usecase.product.mapper;

import com.toyoda.product.dto.product.ProductCreateDto;
import com.toyoda.product.dto.product.ProductDto;
import com.toyoda.product.dto.product.ProductUpdateDto;
import com.toyoda.product.entity.Brand;
import com.toyoda.product.entity.Category;
import com.toyoda.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productMapper(Product product);

    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "category", ignore = true)
    void productMapperByDto(@MappingTarget Product product, ProductUpdateDto productDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "productDto.name")
    @Mapping(target = "enabled", source = "productDto.enabled")
    Product productMapper(ProductCreateDto productDto, Category category, Brand brand);
}
