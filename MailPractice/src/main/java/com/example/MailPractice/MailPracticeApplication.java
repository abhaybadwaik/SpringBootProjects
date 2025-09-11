package com.example.MailPractice;

import com.example.MailPractice.service.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling
@SpringBootApplication
public class MailPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailPracticeApplication.class, args);

    }


}
