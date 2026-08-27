package com.example.demo.exception;
import lombok.Getter;

import java.util.Map;

@Getter
public class BusinessConflictException extends RuntimeException {
    private Map<String, String> fieldError;
    public BusinessConflictException(String message, Map<String, String> fieldError) {
        super(message);
        this.fieldError = fieldError;
    }
}
