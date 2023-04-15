package com.example.demo.core.exception;

public class UseCaseException extends RuntimeException {

    public UseCaseException(String message) {
        super(message);
    }
}