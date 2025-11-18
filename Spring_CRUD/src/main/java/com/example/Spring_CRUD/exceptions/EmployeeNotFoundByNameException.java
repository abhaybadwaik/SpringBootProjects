package com.example.Spring_CRUD.exceptions;

public class EmployeeNotFoundByNameException extends RuntimeException{

    public EmployeeNotFoundByNameException(String message)
    {
        super(message);
    }
}
