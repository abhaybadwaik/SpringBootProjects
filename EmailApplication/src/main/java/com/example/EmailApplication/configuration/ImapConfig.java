package com.example.EmailApplication.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.mail.imap")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImapConfig {
    private String user;
    private String password;
    private String host;
    private String port;
    private String protocol;
    private String sslEnable;

}
