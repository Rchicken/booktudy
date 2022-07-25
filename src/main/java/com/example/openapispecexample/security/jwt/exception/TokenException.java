package com.example.openapispecexample.security.jwt.exception;

import com.example.openapispecexample.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class TokenException extends BusinessException {

    public TokenException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

}
