package com.example.exception;

public class ElementNotFoundException extends DataValidationException {
    public ElementNotFoundException(String menssage) {
        super(menssage, ElementNotFoundException.class.getSimpleName());
        this.setHttpStatus(404);
    }
}
