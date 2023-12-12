package com.oscar.database.exception;

import com.oscar.database.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    ApiResponse response;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFoundException(NotFoundException e) {
        response = new ApiResponse("204", "查無資料", e.getMessage());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(ParameterException.class)
    public ResponseEntity<ApiResponse> handleParameterException(ParameterException e) {
        response = new ApiResponse("422", "參數錯誤", e.getMessage());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        response = new ApiResponse("500", "DB連線異常", e.getMessage());
        return ResponseEntity.ok(response);
    }
}
