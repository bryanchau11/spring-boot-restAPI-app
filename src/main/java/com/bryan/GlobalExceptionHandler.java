package com.bryan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", "Not Found");
        errorMap.put("message", ex.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }
}
