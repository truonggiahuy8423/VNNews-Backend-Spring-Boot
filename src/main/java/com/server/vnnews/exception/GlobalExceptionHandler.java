package com.server.vnnews.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AppRuntimeException.class)
    ResponseEntity<ResponseException> handlingRuntimeException(AppRuntimeException exception) {
        return new ResponseEntity<>(new ResponseException(exception.getMessage(), exception.getCode()), HttpStatus.BAD_REQUEST);
    }
}
