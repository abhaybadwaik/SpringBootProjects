package com.example.ReadingMails.controller;

import com.example.ReadingMails.service.ReadMail;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    ReadMail readingMail;

    @GetMapping("/readMail")
    public ResponseEntity<String> readMails(@RequestParam String mail) throws MessagingException {
        readingMail.readMail(mail);
        return ResponseEntity.ok("MAils got read " + mail);
    }

}
