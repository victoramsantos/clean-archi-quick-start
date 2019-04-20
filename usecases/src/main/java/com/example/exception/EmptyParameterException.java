package com.example.exception;

public class EmptyParameterException extends DataValidationException {

    public EmptyParameterException(String msg) {
        super(msg, EmptyParameterException.class.getSimpleName());
    }

}
