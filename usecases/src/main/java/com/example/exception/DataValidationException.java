package com.example.exception;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

public abstract class DataValidationException extends Exception {
    private String simpleClassName;
    @Getter @Setter private int httpStatus = 400; //Bad Request

    DataValidationException(String message, String simpleClassName){
        super(message);
        this.simpleClassName = simpleClassName;
    }

    @Override
    public String getMessage() {
        Gson gson = new Gson();
        DataValidationExceptionDTO exceptionDTO = new DataValidationExceptionDTO(this.simpleClassName, super.getMessage(), httpStatus);
        return gson.toJson(exceptionDTO);
    }

}
