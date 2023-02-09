package com.example.statisticsservice.exception;

public class NotSupportedTypeException extends RuntimeException {

    public NotSupportedTypeException(String type) {
        super(String.format("Type %s not is not supported", type));
    }
}
