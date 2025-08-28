package com.example.PracticeReadMail.controller;

import com.example.PracticeReadMail.service.EmailReadingService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class emailController {
    @Autowired
    EmailReadingService ers;
    @GetMapping("read")
    public String readMail() throws MessagingException, IOException {
        try {
            ers.readMails();
            return "Email got read Check Console";
        }catch (Exception e){
            return "ERROR: " + e.getMessage();
        }
    }
}
