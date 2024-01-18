package com.toyoda.product.dto.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Integer id;

    @NotEmpty
    private String name;

    @NotNull
    private Boolean enabled;
}
