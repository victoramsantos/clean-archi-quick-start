package com.example.exception;

public class BusinessInvalidException extends DataValidationException {
    public BusinessInvalidException(String message) {
        super(message, BusinessInvalidException.class.getSimpleName());
    }
}
