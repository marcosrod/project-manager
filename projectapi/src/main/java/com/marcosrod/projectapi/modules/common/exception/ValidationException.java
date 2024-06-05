package com.marcosrod.projectapi.modules.common.exception;

public class ValidationException extends RuntimeException{

    public ValidationException(String message) {
        super(message);
    }
}
