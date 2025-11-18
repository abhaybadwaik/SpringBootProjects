package com.example.Spring_CRUD.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String,String>> employeeNotFoundException(EmployeeNotFoundException exception)
    {
        Map<String,String>map=new HashMap<>();
        map.put("message",exception.getMessage());
        map.put("status", HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotFoundByNameException.class)
    public ResponseEntity<Map<String,String>> employeeNotFoundByNameException(EmployeeNotFoundByNameException e)
    {
        Map<String,String>map=new HashMap<>();
        map.put("message",e.getMessage());
        map.put("status",HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidEmployeeAgeException.class)
    public ResponseEntity<String> handleInvalidAge(InvalidEmployeeAgeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }


    @ExceptionHandler(InvalidSalaryUpdateException.class)
    public ResponseEntity<String> handleInvalidSalary(InvalidSalaryUpdateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<String> handleEmployeeAlreadyExists(EmployeeAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }


}
