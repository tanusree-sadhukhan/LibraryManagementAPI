
package com.librarymanagement.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Resource Not Found - 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            ResourceNotFoundException ex) {

        return createResponse(
                HttpStatus.NOT_FOUND, "Not Found", ex.getMessage());
    }

    // Duplicate Resource / Already Returned - 409
    @ExceptionHandler({
        DuplicateResourceException.class,
        IllegalStateException.class
    })
    public ResponseEntity<Map<String, Object>> handleConflict(
            RuntimeException ex) {

        return createResponse(
                HttpStatus.CONFLICT, "Conflict", ex.getMessage());
    }

    // Validation Error - 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, Object> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 400);
        response.put("error", "Validation Failed");
        response.put("messages", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Other Runtime Errors - 400
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(
            RuntimeException ex) {

        return createResponse(
                HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage());
    }

    // Common Response Method
    private ResponseEntity<Map<String, Object>> createResponse(
            HttpStatus status, String errorMessage, String message) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", status.value());
        error.put("error", errorMessage);
        error.put("message", message);

        return new ResponseEntity<>(error, status);
    }
}
