package com.example.UnsecuredSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/public")
    public String publicEndPoint(){
        return "This is Public EndPoint. No Login Required !";
    }

    @GetMapping("/hello")
    public String helloEndPoint(){
        return "Hello, this is a SECURED endpoint 🔐";
    }
}
