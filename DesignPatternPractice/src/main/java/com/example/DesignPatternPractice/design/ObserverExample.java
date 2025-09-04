package com.example.DesignPatternPractice.design;


import java.util.ArrayList;
import java.util.List;

interface Observer//
{
    public abstract  void update(String message);
}

class Subject
{
    List<Observer> list=new ArrayList<>();

    public void addObserver(Observer observer)
    {
        list.add(observer);
    }

    public void removeObserver(Observer observer)
    {
        list.remove(observer);
    }

    public void notifyObserver(String message)
    {
        for(Observer observer:list)
        {
            observer.update(message);
        }
    }
}

class SMSService implements Observer
{

    @Override
    public void update(String message) {
        System.out.println("SMS Sent "+message);
    }
}

class WhatsUpService implements Observer
{

    @Override
    public void update(String message) {
        System.out.println("What's up message sent "+message);
    }
}
public class ObserverExample {
    public static void main(String[] args) {
        Subject subject=new Subject();
        subject.addObserver(new WhatsUpService());
        subject.addObserver(new SMSService());

        subject.notifyObserver("Message sent");
    }
}
