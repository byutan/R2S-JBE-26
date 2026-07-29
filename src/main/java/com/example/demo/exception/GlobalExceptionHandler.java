package com.example.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIError> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not found",
                e.getMessage(),
                req.getRequestURI(),
                null
                )
        );
    }

    @ExceptionHandler(BusinessConflictException.class)
    public  ResponseEntity<APIError> handleBusinessConflictException(BusinessConflictException e, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new APIError(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                e.getMessage(),
                req.getRequestURI(),
                null
            )
        );
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<APIError> handleBusinessException(BusinessException e, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad request",
                e.getMessage(),
                req.getRequestURI(),
                null
            )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest req) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation error",
                errorMessage,
                req.getRequestURI(),
                null
            )
        );
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<APIError> handleBadRequest(IllegalArgumentException e, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad request",
                e.getMessage(),
                req.getRequestURI(),
                null
        ));
    }
}
