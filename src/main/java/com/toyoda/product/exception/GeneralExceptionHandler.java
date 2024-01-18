package com.toyoda.product.exception;

import com.toyoda.product.dto.exception.DefaultExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<DefaultExceptionDto> handleBaseException(NotFoundException e) {
        DefaultExceptionDto defaultExceptionDto = new DefaultExceptionDto();
        defaultExceptionDto.setMessage(e.getMessage());
        defaultExceptionDto.setTimestamp(LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(defaultExceptionDto);
    }
}
