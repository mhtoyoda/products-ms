package com.toyoda.product.dto.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {

    @NotEmpty
    private String name;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Boolean enabled;
}
