package com.example.EmailApplication.service;

import com.example.EmailApplication.configuration.ImapConfig;
import com.example.EmailApplication.dto.DtoClass;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.UUID;
@Service
public class EmailServices {
    @Autowired
    JavaMailSender javaMailSender;
    ImapConfig imapConfig;
    public void sendMail(DtoClass dtoClass){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(dtoClass.getTo());
        simpleMailMessage.setCc(dtoClass.getCc());
        simpleMailMessage.setBcc(dtoClass.getBcc());
        simpleMailMessage.setSubject(dtoClass.getSubject());
        simpleMailMessage.setText(dtoClass.getText());
        javaMailSender.send(simpleMailMessage);
    }
    public void SendMemeMail(DtoClass dtoClass) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setTo(dtoClass.getTo());
        mimeMessageHelper.setCc(dtoClass.getCc());
        mimeMessageHelper.setBcc(dtoClass.getBcc());
        mimeMessageHelper.setSubject(dtoClass.getSubject());
        mimeMessageHelper.setText(dtoClass.getText());
        for(int i=0; i< dtoClass.getFile().length; i++ ) {
            mimeMessageHelper.addAttachment(dtoClass.getFile()[i].getOriginalFilename(), dtoClass.getFile()[i], dtoClass.getFile()[i].getContentType());
        }
        javaMailSender.send(mimeMessage);
//        mimeMessageHelper.setText(dtoClass.getHtmlText(),true);
//        String cid = UUID.randomUUID().toString();
//        mimeMessageHelper.addInline(cid,);

    }

    public void readMail() throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.imap.host",imapConfig.getHost());
        properties.put("mail.imap.port",imapConfig.getPort());
        properties.put("mail.imap.protocol",imapConfig.getProtocol());
        properties.put("ssl.enable",imapConfig.getSslEnable());

        Session instance = Session.getInstance(properties);
        Store store = instance.getStore("imaps");
        store.connect(imapConfig.getHost(), imapConfig.getUser(), imapConfig.getPassword());
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        int messageCount = inbox.getMessageCount();
        System.out.println(messageCount);
        inbox.close();
        store.close();
    }


}
