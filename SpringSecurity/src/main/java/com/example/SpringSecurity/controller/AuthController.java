package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.dto.AuthRequest;
import com.example.SpringSecurity.dto.UserRequestDto;
import com.example.SpringSecurity.entity.User;
import com.example.SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(userService.register(userRequestDto));
    }
    @GetMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest)
    {
        return ResponseEntity.ok(userService.authenticate(authRequest));
    }
//    @PostMapping("/refresh")
//    public ResponseEntity<String>
}
