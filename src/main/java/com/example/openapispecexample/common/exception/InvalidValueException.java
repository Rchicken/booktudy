package com.example.openapispecexample.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidValueException extends BusinessException {

    public InvalidValueException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
