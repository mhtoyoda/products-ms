package com.toyoda.product.usecase.brand.mapper;

import com.toyoda.product.dto.brand.BrandDto;
import com.toyoda.product.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandDto brandMapper(Brand brand);

    @Mapping(target = "id", ignore = true)
    void brandMapperByDto(@MappingTarget Brand brand, BrandDto brandDto);
}
