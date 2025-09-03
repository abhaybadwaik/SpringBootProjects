package com.example.DesignPatternPractice.service;

import lombok.Data;

import java.util.Date;

public interface ServiceImp {

    public abstract String CreationalServices(String message);
    public abstract String date(Date date);
}
