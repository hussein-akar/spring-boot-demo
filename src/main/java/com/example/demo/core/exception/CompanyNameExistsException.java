package com.example.demo.core.exception;

public class CompanyNameExistsException extends UseCaseException {

    public CompanyNameExistsException() {
        super("Company name already exists.");
    }
}