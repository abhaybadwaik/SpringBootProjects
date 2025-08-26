package com.example.ReadingMails.service;

import com.example.ReadingMails.Configuration.ImapConfig;
import jakarta.mail.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Properties;

@Service
public class ReadMail {
    @Autowired
    ImapConfig imapConfig;
    public void readMail(String mail) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.imap.host","imap.gmail.com");
        properties.put("mail.imap.port","993");
        properties.put("mail.imap.protocol","imaps");
        properties.put("ssl.enable","true");

        Session instance = Session.getInstance(properties);
        Store store = instance.getStore("imaps");
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        int messageCount = inbox.getMessageCount();
        System.out.println(messageCount);
        int max = Math.max(messageCount - 1,1);
//        Message[] messages = inbox.getMessages();
//        System.out.println(messages.length);
//        Arrays.stream(messages).forEach(msg-> {
//            try {
//                System.out.println(msg.getMessageNumber()+" "+msg.getReceivedDate());
//            } catch (MessagingException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        Folder[] list = store.getDefaultFolder().list("*");
//        Arrays.stream(list).forEach(System.out::println);

        inbox.close();
        store.close();

    }
}
