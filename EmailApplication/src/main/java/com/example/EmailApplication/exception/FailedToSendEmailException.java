package com.example.EmailApplication.exception;

public class FailedToSendEmailException extends RuntimeException{
    public FailedToSendEmailException(String message){
        super(message);
    }
}
