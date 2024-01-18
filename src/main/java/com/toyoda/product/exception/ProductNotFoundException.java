package com.toyoda.product.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends NotFoundException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
