package com.example.DesignPatternPractice.design;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class FactoryBeanConfig {

    public static void main(String[] args) throws Exception {
        PersonBean personBean=new PersonBean();
        Person object = personBean.getObject();
        System.out.println(object);
        Person object1 = personBean.getObject();
        System.out.println(object1==object);
    }
}
