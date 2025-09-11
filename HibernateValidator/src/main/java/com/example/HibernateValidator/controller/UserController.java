package com.example.HibernateValidator.controller;

import com.example.HibernateValidator.entity.UserEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/input")
    public ResponseEntity<String> isValid(@Valid @RequestBody UserEntity userEntity){
        return new ResponseEntity<>(userEntity.toString(), HttpStatus.OK);
    }
}
