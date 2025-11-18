package com.example.Spring_CRUD.exceptions;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(String message)
    {
        super(message);
    }
}
