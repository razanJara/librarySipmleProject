package com.library.library.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleApiException(CustomException ex) {
        logger.severe("Handled CustomException: " + ex);
        ErrorResponse errorResponse = new ErrorResponse(ex.getStatus().value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }
}
