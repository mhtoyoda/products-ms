package com.toyoda.product.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilterDto {

    private String name;
    private String categoryName;
    private String brandName;
}
