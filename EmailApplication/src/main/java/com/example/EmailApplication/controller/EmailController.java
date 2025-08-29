package com.example.EmailApplication.controller;

import com.example.EmailApplication.dto.DtoClass;
import com.example.EmailApplication.service.EmailServices;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class EmailController {
    @Autowired
    EmailServices emailServices;

    @PostMapping("/sendMail")
    public ResponseEntity<String> sendMails(@RequestParam String [] to,
                                            @RequestParam String [] cc,
                                            @RequestParam String [] bcc,
                                            @RequestParam String subject,
                                            @RequestParam String text,
                                            @RequestParam(required = false) MultipartFile file){
        DtoClass dtoClass = new DtoClass(to,cc,bcc,subject,text,null);
        emailServices.sendMail(dtoClass);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    @PostMapping("/sendMime")
    public ResponseEntity<String> mimeMail(@RequestParam String [] to,
                                           @RequestParam String [] cc,
                                           @RequestParam String [] bcc,
                                           @RequestParam String subject,
                                           @RequestParam String text,
                                           @RequestParam MultipartFile [] file) throws MessagingException {
        DtoClass dtoClass = new DtoClass(to,cc,bcc,subject,text,file);
        emailServices.SendMemeMail(dtoClass);
        return new ResponseEntity<>("Attachment sent Succefully",HttpStatus.OK);

    }
    @GetMapping("/readMails")
    public ResponseEntity<String> readMails() throws MessagingException, IOException {
        emailServices.readMail();
        return new  ResponseEntity<>("Mails got read check Console",HttpStatus.OK);
    }

    @GetMapping("/readLastFiveMails")
    public ResponseEntity<String> readLastFiveMails() throws MessagingException, IOException {
        emailServices.readLastFiveMails();
        return new ResponseEntity<>("First Five Mail Got Read Check Console",HttpStatus.OK);
    }

    @GetMapping("/readFirstFiveMail")
    public ResponseEntity<String> FirstFive() throws MessagingException, IOException {
        emailServices.readFirstFiveMail();
        return new ResponseEntity<>("Last Five LAtest mail Check console",HttpStatus.OK);
    }

    @GetMapping("/recentUnseenMail")
    public ResponseEntity<String> recentUnseenMail() throws MessagingException, IOException {
        emailServices.recentFiveUnseenMail();
        return new ResponseEntity<>("Check Recent Unseen Mail in IntelleJ Console",HttpStatus.OK);
    }

    @GetMapping("lastFiveUnseenMail")
    public ResponseEntity<String> lastUnseen() throws MessagingException, IOException {
        emailServices.lastFiveUnseenMail();
        return new ResponseEntity<>("Last five Unseen Mails Check Console ",HttpStatus.OK);
    }
}
