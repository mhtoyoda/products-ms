package com.toyoda.product.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultExceptionDto {

    private String message;
    private String timestamp;
}
