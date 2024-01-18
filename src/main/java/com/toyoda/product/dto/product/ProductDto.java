package com.toyoda.product.dto.product;

import com.toyoda.product.dto.brand.BrandDto;
import com.toyoda.product.dto.category.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String name;
    private int quantity;
    private BigDecimal price;
    private boolean enabled;
    private CategoryDto category;
    private BrandDto brand;
}
