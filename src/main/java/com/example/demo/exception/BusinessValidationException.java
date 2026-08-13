package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;
@Getter
public class BusinessValidationException extends RuntimeException {
    private Map<String, String> fieldError;
    private HttpStatus code;
    public BusinessValidationException(String message, HttpStatus code, Map<String, String> fieldError) {
        super(message);
        this.fieldError = fieldError;
        this.code = code;
    }
}
