package com.example.SpringSecurityFormat.controller;


import com.example.SpringSecurityFormat.dto.AuthRequest;
import com.example.SpringSecurityFormat.dto.UserRequestDto;
import com.example.SpringSecurityFormat.entity.User;
import com.example.SpringSecurityFormat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRequestDto userRequestDto)
    {
        return ResponseEntity.ok(userService.register(userRequestDto));
    }

    @GetMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest)
    {
        return ResponseEntity.ok(userService.authenticate(authRequest));
    }
}
