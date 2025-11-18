package com.example.Spring_CRUD.exceptions;

public class InvalidEmployeeAgeException extends RuntimeException {
    public InvalidEmployeeAgeException(String message) {
        super(message);
    }
}
