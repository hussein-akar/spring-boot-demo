package com.example.demo.core.exception.handler;

import com.example.demo.core.exception.ForbiddenException;
import com.example.demo.core.exception.UseCaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(UseCaseException.class)
    public ResponseEntity<Object> handleShowMessageToUserException(UseCaseException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handle(RuntimeException ex) {
        return new ResponseEntity<>("Sorry, something went wrong!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}