package com.library.library.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException{
    private final HttpStatus status;

    public CustomException(String message, int status) {
        super(message);
        this.status = HttpStatus.valueOf(status);
    }

}
