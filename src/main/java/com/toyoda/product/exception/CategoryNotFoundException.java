package com.toyoda.product.exception;

import lombok.Getter;

@Getter
public class CategoryNotFoundException extends NotFoundException {

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
