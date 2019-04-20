package com.example.exception;

public class DataValidationExceptionDTO {
    private String message;
    private String simpleClassName;
    private int http_status;

    public DataValidationExceptionDTO(String simpleClassName, String message, int http_status) {
        this.simpleClassName = simpleClassName;
        this.message = message;
        this.http_status = http_status;
    }
}
