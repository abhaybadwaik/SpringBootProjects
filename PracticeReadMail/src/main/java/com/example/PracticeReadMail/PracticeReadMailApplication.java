package com.example.PracticeReadMail;

import com.example.PracticeReadMail.Congifuration.ImapConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(ImapConfig.class)
@SpringBootApplication
public class PracticeReadMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeReadMailApplication.class, args);
	}

}
