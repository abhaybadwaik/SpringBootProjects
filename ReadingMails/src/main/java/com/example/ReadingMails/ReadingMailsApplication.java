package com.example.ReadingMails;

import com.example.ReadingMails.Configuration.ImapConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(ImapConfig.class)
@SpringBootApplication
public class ReadingMailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingMailsApplication.class, args);
	}

}
