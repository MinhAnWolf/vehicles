package com.example.common.config;

import com.example.common.model.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class RestExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ExceptionHandler.SystemErrorException.class, ExceptionHandler.BadRequestException.class,
            Exception.class, RuntimeException.class})
    public ResponseEntity<ApiError> handleException(ExceptionHandler e) {
        System.out.println(e.outputError());
        return e.outputError();
    }
}