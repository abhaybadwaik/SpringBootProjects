package com.example.DesignPatternPractice.design;


interface Notification
{
    public abstract Notification send();
}

class SMS implements Notification
{

    @Override
    public SMS send() {
        System.out.println("SMS");
        return new SMS();
    }
}

class Email implements Notification
{

    @Override
    public Email send() {
        System.out.println("Email");
        return new Email();
    }
}
public class AbstractPattern {

    public static void main(String[] args) {
        SMS sms = new SMS();
        sms.send();
        Email email = new Email();
        email.send();
    }
}
