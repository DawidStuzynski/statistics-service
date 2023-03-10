package com.example.statisticsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseBody
    @ExceptionHandler(NotSupportedTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notSupportedTypeExceptionHandler(NotSupportedTypeException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String resourceNotFoundException(ResourceNotFoundException e) {
        return e.getMessage();
    }
}
