package com.example.EmailApplication.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends  RuntimeException{

    @ExceptionHandler(FailedToSendEmailException.class)

    public ResponseEntity<String> handleFailedToSendEmail(FailedToSendEmailException ex){
        log.error("Email Sending Failed beacuse:- ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
