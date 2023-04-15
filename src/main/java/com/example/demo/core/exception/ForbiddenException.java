package com.example.demo.core.exception;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super("Access Forbidden.");
    }
}