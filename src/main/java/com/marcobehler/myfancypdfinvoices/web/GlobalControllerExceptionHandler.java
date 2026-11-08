package com.marcobehler.myfancypdfinvoices.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice 
//Applys to all the @Controllers and @RestController it knows
public class GlobalControllerExceptionHandler {

    @ResponseStatus (HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return "Sorry, that was not quite right: " + e.getMessage();
    }
    
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolation(ConstraintViolationException  e) {
        return "Sorry, that was not quite right: " + e.getMessage();
    }
}
