package com.example.demo.controllers;

import com.example.demo.exceptions.TestRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handle(Exception e) {
        System.out.println("HELLO SIMPLE EXCEPTION HANDLER");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity handle(RuntimeException e) {
        System.out.println("HELLO RUNTIME EXCEPTION HANDLER");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TestRuntimeException.class)
    public ResponseEntity handle(TestRuntimeException e) {
        System.out.println("HELLO TEST RUNTIME EXCEPTION HANDLER");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
