package com.example.MailPractice.Controller;

import com.example.MailPractice.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {

    @Autowired
    EmailService emailService;

    @PostMapping("/email")
    public ResponseEntity<String> sendMail(@RequestParam String to) throws MessagingException {
        emailService.sendMails(to);
        return ResponseEntity.ok("Mail sent Successfully to = " + to);
    }
}
