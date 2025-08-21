package com.example.ReviewProject.Exception;


public class DataAlreadyExistsException extends RuntimeException {
    public DataAlreadyExistsException() {
        super("Data already exists"); }
    public DataAlreadyExistsException(String message) {
        super(message); }
}
