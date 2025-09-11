package com.example.PracticeReadMail.service;

import com.example.PracticeReadMail.Congifuration.ImapConfig;
import jakarta.mail.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

@Service
public class EmailReadingService {
    @Autowired
    ImapConfig imapConfig;
    public void readMails() throws MessagingException, IOException {
        Properties properties = new Properties();
        properties.put("mail.imap.host","imap.gmail.com");
        properties.put("mail.imap.port","993");
        properties.put("mail.imap.protocol","imaps");
        properties.put("ssl.enable","true");

        Session emailSession = Session.getDefaultInstance(properties);
        Store store = emailSession.getStore("imaps");
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        Message [] messages = inbox.getMessages();
        for (Message message : messages){
            System.out.println("Subjects:- " + message.getSubject());
            System.out.println("From:- " + Arrays.toString(message.getFrom()));
            Object content = message.getContent();
            System.out.println("Content: " + content.toString());
        }
        inbox.close(false);
        store.close();
    }
}
