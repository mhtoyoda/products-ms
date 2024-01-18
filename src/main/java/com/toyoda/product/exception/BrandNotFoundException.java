package com.toyoda.product.exception;

import lombok.Getter;

@Getter
public class BrandNotFoundException extends NotFoundException {

    public BrandNotFoundException(String message) {
        super(message);
    }
}
