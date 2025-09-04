package com.example.DesignPatternPractice.design;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
 public class PersonConfig
{
    @Bean
    public PersonBean person()
    {
        PersonBean personBean=new PersonBean();
        personBean.setName("Mahesh");
        personBean.setAge(23);
        return personBean;
    }
}
