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
import java.util.Date;

@RestController
public class EmailController {
    @Autowired
    EmailServices emailServices;

    @PostMapping("/sendMail")
    public ResponseEntity<String> sendMails(@RequestParam String [] to,
                                            @RequestParam String [] cc,
                                            @RequestParam String [] bcc,
                                            @RequestParam String subject,
                                            @RequestParam String text){
        DtoClass dtoClass = new DtoClass(to,cc,bcc,subject,text);
        emailServices.sendMail(dtoClass);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    @PostMapping("/sendMime")
    public ResponseEntity<String> mimeMail(@RequestParam String [] to,
                                           @RequestParam String [] cc,
                                           @RequestParam String [] bcc,
                                           @RequestParam String subject,
                                           @RequestParam String text) throws MessagingException, InterruptedException {
        DtoClass dtoClass = new DtoClass(to,cc,bcc,subject,text);
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

    @GetMapping("/lastFiveUnseenMail")
    public ResponseEntity<String> lastUnseen() throws MessagingException, IOException {
        emailServices.lastFiveUnseenMail();
        return new ResponseEntity<>("Last five Unseen Mails Check Console ",HttpStatus.OK);
    }

    @GetMapping("/searchBySender")
    public ResponseEntity<String> senderMails(@RequestParam String sender) throws MessagingException, IOException {
        emailServices.getEmailsFromSender(sender);
        return new ResponseEntity<>("Mails got searched as per gmail input check Console",HttpStatus.OK);
    }

    @GetMapping("/searchByFilter")
    public ResponseEntity<String> readMailWithFilters(@RequestParam String subject,
                                                      @RequestParam String recipientEmail,
                                                      @RequestParam Date afterDate) throws MessagingException, IOException {
        emailServices.readMailsWithFilters(subject,recipientEmail,afterDate);
        return new ResponseEntity<>("Filtered Mail Executed Check Console",HttpStatus.OK);
    }

    @GetMapping("/downloadAttachment")
    public String downloadAttachments(@RequestParam String saveDir) {
        try {
            emailServices.downloadAttachments(saveDir);
            return "✅ Attachments downloaded successfully to: " + saveDir;
        } catch (Exception e) {
            return "❌ Failed to download attachments: " + e.getMessage();
        }
    }
}
