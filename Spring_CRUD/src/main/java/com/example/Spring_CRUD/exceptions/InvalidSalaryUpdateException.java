package com.example.Spring_CRUD.exceptions;


public class InvalidSalaryUpdateException extends RuntimeException {
    public InvalidSalaryUpdateException(String message) {
        super(message);
    }
}
