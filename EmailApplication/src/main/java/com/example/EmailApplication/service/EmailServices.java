package com.example.EmailApplication.service;

import com.example.EmailApplication.configuration.ImapConfig;
import com.example.EmailApplication.dto.DtoClass;
import com.example.EmailApplication.exception.FailedToSendEmailException;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.search.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            throw new FailedToSendEmailException("Failed to send email to : " + Arrays.toString(simpleMailMessage.getTo()));
        }
    }


    public void SendMemeMail(DtoClass dtoClass) throws MessagingException, InterruptedException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setTo(dtoClass.getTo());
        mimeMessageHelper.setCc(dtoClass.getCc());
        mimeMessageHelper.setBcc(dtoClass.getBcc());
        mimeMessageHelper.setSubject(dtoClass.getSubject());
        mimeMessageHelper.setText(dtoClass.getText(), true);
//        for (int i = 0; i < dtoClass.getFile().length; i++) {
//            mimeMessageHelper.addAttachment(dtoClass.getFile()[i].getOriginalFilename(), dtoClass.getFile()[i], dtoClass.getFile()[i].getContentType());
//        }
//        File file1 = new File("\"C:\\Users\\ABHAY\\Downloads\\products.pdf\"");
        ClassPathResource classPathResource = new ClassPathResource("docs/products.pdf");
        mimeMessageHelper.addAttachment("sumith.pdf",classPathResource);

//        MultipartFile[] multipartFiles = dtoClass.getFile();
        String text = dtoClass.getText();

//        for (MultipartFile file : multipartFiles) {
//            String cid = UUID.randomUUID().toString();
//            mimeMessageHelper.addInline(cid, file, file.getContentType());
//            text = text.replaceFirst("abc", cid);
//        }


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

    @Async("threadPoolTaskExecutor")
    public void getEmailsFromSender(String sender) throws MessagingException, IOException {
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

        SearchTerm searchTerm = new FromStringTerm(sender);
        Message[] messages = inbox.search(searchTerm);
        System.out.println("Total mails from "+ sender + " is : " + messages.length + "\n");

        for (Message message : messages) {
            System.out.println("Subjects :-" + message.getSubject());
            System.out.println("From :- " + Arrays.toString(message.getFrom()));
            System.out.println("Date :- " + message.getReceivedDate());

        }
        inbox.close();
        store.close();
    }

    @Async("threadPoolTaskExecutor")
    public void readMailsWithFilters(String subject, String recipientEmail, Date afterDate) throws MessagingException, IOException {
        Properties properties = new Properties();
        properties.put("mail.imap.host",imapConfig.getHost());
        properties.put("mail.imap.port",imapConfig.getPort());
        properties.put("mail.imap.protocol",imapConfig.getProtocol());
        properties.put("mail.imap.ssl.enable",imapConfig.getSslEnable());

        Session session= Session.getInstance(properties);
        Store store = session.getStore(imapConfig.getProtocol());
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        List<SearchTerm> terms = new ArrayList<>();

        if (subject != null && !subject.isEmpty()){
            terms.add(new SubjectTerm(subject));
        }
        if (recipientEmail != null && !recipientEmail.isEmpty()){
            terms.add(new RecipientTerm(Message.RecipientType.TO,new InternetAddress(recipientEmail)));
        }
        if (afterDate != null){
            terms.add(new SentDateTerm(ComparisonTerm.GT,afterDate));
        }

        SearchTerm finalTerm;
        if (terms.size()==1){
            finalTerm = terms.get(0);
        } else {
            finalTerm = new AndTerm(terms.toArray(new SearchTerm[0]));
        }

        Message[] filtered = inbox.search(finalTerm);
        for (Message message : filtered) {
            System.out.println("Subject: " + message.getSubject());
            System.out.println("From: " + Arrays.toString(message.getFrom()));
            System.out.println("Date: " + message.getReceivedDate());
            System.out.println("Content" + message.getContent());
            System.out.println("-----");
        }

        inbox.close();
        store.close();
    }

    @Async("threadPoolTaskExecutor")
    public void downloadAttachments(String saveDir) throws Exception {
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

        for (Message message : messages) {
            if (message.getContentType().contains("multipart")) {
                Multipart multipart = (Multipart) message.getContent();

                for (int i = 0; i < multipart.getCount(); i++) {
                    BodyPart bodyPart = multipart.getBodyPart(i);

                    // Check if it has an attachment
                    if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())
                            || bodyPart.getFileName() != null) {

                        String fileName = bodyPart.getFileName();
                        File file = new File(saveDir + File.separator + fileName);

                        try (InputStream is = bodyPart.getInputStream();
                             FileOutputStream fos = new FileOutputStream(file)) {

                            byte[] buf = new byte[4096];
                            int bytesRead;
                            while ((bytesRead = is.read(buf)) != -1) {
                                fos.write(buf, 0, bytesRead);
                            }
                            System.out.println("✅ Saved attachment: " + file.getAbsolutePath());
                        }
                    }
                }
            }
        }

        inbox.close(false);
        store.close();
    }

}
