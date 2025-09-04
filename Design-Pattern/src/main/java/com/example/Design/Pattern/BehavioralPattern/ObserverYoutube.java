package com.example.Design.Pattern.BehavioralPattern;

import java.util.ArrayList;
import java.util.List;

interface Subscriber{
    void update(String videoTitle);
}

class User implements Subscriber{
    private String name;

    public User(String name){
        this.name=name;
    }
    @Override
    public void update(String videoTitle){
        System.out.println(name+" got notified and new video uploaded- " + videoTitle);
    }
}

class Channel{
    private List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber s){
        subscribers.add(s);
    }
    public void unsubscribe(Subscriber s){
        subscribers.remove(s);
    }
    public void uploadVideo(String title){
        System.out.println("Channel Uploaded:- " + title);
        notifySubscriber(title);
    }
    private void notifySubscriber(String title){
        for(Subscriber s : subscribers){
            s.update(title);
        }
    }
}

public class ObserverYoutube {
    public static void main(String[] args) {
        Channel channel = new Channel();

        Subscriber s1 = new User("ABhAY");
        Subscriber s2 = new User("Sumith");
        Subscriber s3 = new User("abhsishek");

        channel.subscribe(s1);
        channel.unsubscribe(s2);
        channel.subscribe(s3);

        channel.uploadVideo("Observer Pattern Explain");
    }
}
