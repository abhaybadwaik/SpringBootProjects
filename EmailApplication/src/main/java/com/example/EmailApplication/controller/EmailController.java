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

@RestController
public class EmailController {
    @Autowired
    EmailServices emailServices;
    @PostMapping("sendMail")
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
    @PostMapping("sendMime")
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
    @GetMapping("readMails")
    public ResponseEntity<String> readMails() throws MessagingException {
        emailServices.readMail();
        return
    }
}
