package com.example.DesignPatternPractice.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
//SINGLETON
//@Scope("prototype")
public class CreationalPattern implements ServiceImp {
    public CreationalPattern(){
        System.out.println("Creational pattern instance created");
    }
    @Override
    public String CreationalServices(String message){
        return "Log: " + message;
    }

    @Override
    public String date(Date date)
    {
        return null;
    }
}

@Configuration
class Config
{
    @Bean
    public CreationalPattern check()
    {
        return new CreationalPattern();
    }
}
