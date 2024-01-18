package com.toyoda.product.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {

    @NotEmpty
    private String name;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private Boolean enabled;

    @NotNull
    private Integer categoryId;

    @NotNull
    private Integer brandId;
}
