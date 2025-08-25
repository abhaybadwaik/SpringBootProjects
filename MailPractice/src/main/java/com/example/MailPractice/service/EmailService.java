package com.example.MailPractice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.File;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMails(String to) throws MessagingException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("This is My First Mail Sending");
        simpleMailMessage.setText("HOW YOU FEELING !!?");
//        javaMailSender.send(simpleMailMessage);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject("Test MeMe Message");
        File file = new File("C:\\Users\\ABHAY\\Downloads\\gods.jpg");
        mimeMessageHelper.addAttachment("gods.jpg",file);
        mimeMessageHelper.setText("abc");
        javaMailSender.send(mimeMessage);

    }
}

