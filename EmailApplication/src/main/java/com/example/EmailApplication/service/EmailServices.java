package com.example.EmailApplication.service;

import com.example.EmailApplication.configuration.ImapConfig;
import com.example.EmailApplication.dto.DtoClass;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.search.FlagTerm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

@Service
public class EmailServices {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    ImapConfig imapConfig;

    @Async("threadPoolTaskExecutor")
    public void sendMail(DtoClass dtoClass) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(dtoClass.getTo());
        simpleMailMessage.setCc(dtoClass.getCc());
        simpleMailMessage.setBcc(dtoClass.getBcc());
        simpleMailMessage.setSubject(dtoClass.getSubject());
        simpleMailMessage.setText(dtoClass.getText());
        javaMailSender.send(simpleMailMessage);
    }

    @Async("threadPoolTaskExecutor")
    public void SendMemeMail(DtoClass dtoClass) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setTo(dtoClass.getTo());
        mimeMessageHelper.setCc(dtoClass.getCc());
        mimeMessageHelper.setBcc(dtoClass.getBcc());
        mimeMessageHelper.setSubject(dtoClass.getSubject());
        mimeMessageHelper.setText(dtoClass.getText(), true);
        for (int i = 0; i < dtoClass.getFile().length; i++) {
            mimeMessageHelper.addAttachment(dtoClass.getFile()[i].getOriginalFilename(), dtoClass.getFile()[i], dtoClass.getFile()[i].getContentType());
        }
        MultipartFile[] multipartFiles = dtoClass.getFile();

        String text = dtoClass.getText();

        for (MultipartFile file : multipartFiles) {
            String cid = UUID.randomUUID().toString();
            mimeMessageHelper.addInline(cid, file, file.getContentType());
            text = text.replaceFirst("abc", cid);
        }
        mimeMessageHelper.setText(text, true);
        javaMailSender.send(mimeMessage);
    }

    @Async("threadPoolTaskExecutor")
    public void readMail() throws MessagingException, IOException {
        Properties properties = new Properties();
        properties.put("mail.imap.host", imapConfig.getHost());
        properties.put("mail.imap.port", imapConfig.getPort());
        properties.put("mail.imap.protocol", imapConfig.getProtocol());
        properties.put("ssl.enable", imapConfig.getSslEnable());

        Session instance = Session.getInstance(properties);
        Store store = instance.getStore("imaps");
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        int messageCount = inbox.getMessageCount();
        System.out.println(messageCount);

        Message[] messages = inbox.getMessages();
        for (Message message : messages) {
            System.out.println("Subjects :-" + message.getSubject());
            System.out.println("From :- " + Arrays.toString(message.getFrom()));
            Object content = message.getContent();
            System.out.println("Content:- " + content.toString());
        }

        inbox.close();
        store.close();
    }

    @Async("threadPoolTaskExecutor")
    public void readLastFiveMails() throws MessagingException, IOException {
        Properties properties = new Properties();
        properties.put("mail.imap.host", imapConfig.getHost());
        properties.put("mail.imap.port", imapConfig.getPort());
        properties.put("mail.store.protocol", imapConfig.getProtocol());
        properties.put("mail.imap.ssl.enable", imapConfig.getSslEnable());

        Session session = Session.getInstance(properties);
        Store store = session.getStore(imapConfig.getProtocol());
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();

        // Loop only first 5 mails (or less if not enough)
        for (int i = 0; i < Math.min(5, messages.length); i++) {
            Message message = messages[i];
            System.out.println("Subject: " + message.getSubject());
            System.out.println("From: " + Arrays.toString(message.getFrom()));
            Object content = message.getContent();
            System.out.println("Content: " + content.toString());
            System.out.println("-----");
        }
        inbox.close();
        store.close();
    }

    @Async("threadPoolTaskExecutor")
    public void readFirstFiveMail() throws MessagingException, IOException {
        Properties properties = new Properties();
        properties.put("mail.imap.host", imapConfig.getHost());
        properties.put("mail.imap.port", imapConfig.getPort());
        properties.put("mail.store.protocol", imapConfig.getProtocol());
        properties.put("mail.imap.ssl.enable", imapConfig.getSslEnable());

        Session session = Session.getInstance(properties);
        Store store = session.getStore(imapConfig.getProtocol());
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();
        int total = messages.length;
        int start = Math.max(0, total - 5);

        for (int i = start; i < total; i++) {
            Message message = messages[i];
            System.out.println("Subject: " + message.getSubject());
            System.out.println("From: " + Arrays.toString(message.getFrom()));
            Object content = message.getContent();
            System.out.println("Content: " + content.toString());
            System.out.println("-----");
        }
        inbox.close();
        store.close();
    }

    @Async("threadPoolTaskExecutor")
    public void recentFiveUnseenMail() throws MessagingException, IOException {
        Properties properties = new Properties();
        properties.put("mail.imap.host", imapConfig.getHost());
        properties.put("mail.imap.port", imapConfig.getPort());
        properties.put("mail.store.protocol", imapConfig.getProtocol());
        properties.put("mail.imap.ssl.enable", imapConfig.getSslEnable());

        Session session = Session.getInstance(properties);
        Store store = session.getStore(imapConfig.getProtocol());
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        FlagTerm unseenFlagTerm = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
        Message[] messages = inbox.search(unseenFlagTerm);


        int total = messages.length;
        int start = Math.max(0, total - 5);

        for (int i = start; i < total; i++) {
            Message message = messages[i];
            System.out.println("Subject: " + message.getSubject());
            System.out.println("From: " + Arrays.toString(message.getFrom()));
            Object content = message.getContent();
            System.out.println("Content: " + content.toString());
            System.out.println("-----");
        }
        inbox.close();
        store.close();
    }

    @Async("threadPoolTaskExecutor")
    public void lastFiveUnseenMail() throws MessagingException, IOException {
        Properties properties = new Properties();
        properties.put("mail.imap.host", imapConfig.getHost());
        properties.put("mail.imap.port", imapConfig.getPort());
        properties.put("mail.store.protocol", imapConfig.getProtocol());
        properties.put("mail.imap.ssl.enable", imapConfig.getSslEnable());

        Session session = Session.getInstance(properties);
        Store store = session.getStore(imapConfig.getProtocol());
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        FlagTerm unseenFlagTerm = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
        Message[] messages = inbox.search(unseenFlagTerm);

        // Loop only last 5 mails (or less if not enough)
        for (int i = 0; i < Math.min(5, messages.length); i++) {
            Message message = messages[i];
            System.out.println("Subject: " + message.getSubject());
            System.out.println("From: " + Arrays.toString(message.getFrom()));
            Object content = message.getContent();
            System.out.println("Content: " + content.toString());
            System.out.println("-----");
        }
        inbox.close();
        store.close();
    }

    public void fetchMailsByFilters() throws MessagingException, IOException {
        Properties properties = new Properties();
        properties.put("mail.imap.host", imapConfig.getHost());
        properties.put("mail.imap.port", imapConfig.getPort());
        properties.put("mail.imap.protocol", imapConfig.getProtocol());
        properties.put("ssl.enable", imapConfig.getSslEnable());

        Session instance = Session.getInstance(properties);
        Store store = instance.getStore("imaps");
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        int messageCount = inbox.getMessageCount();
        System.out.println(messageCount);

        Message[] messages = inbox.getMessages();
        
        for (Message message : messages) {
            System.out.println("Subjects :-" + message.getSubject());
            System.out.println("From :- " + Arrays.toString(message.getFrom()));
            Object content = message.getContent();
            System.out.println("Content:- " + content.toString());
        }

        inbox.close();
        store.close();
    }
}
