package com.ridoy.villa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice makes this class a global exception handler for REST controllers
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle a custom exception, for example when a Villa is not found
    @ExceptionHandler(VillaNotFoundException.class)
    public ResponseEntity<String> handleVillaNotFoundException(VillaNotFoundException ex) {
        // Return a 404 Not Found status with a custom error message
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Handle generic Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        // Return a 500 Internal Server Error with a generic error message
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }

    // Handle a more specific exception, such as IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // Return a 400 Bad Request status with a custom error message
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: " + ex.getMessage());
    }
}
