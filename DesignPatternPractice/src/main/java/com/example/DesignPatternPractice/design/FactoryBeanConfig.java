package com.example.DesignPatternPractice.design;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person
{
    private String name;
    private int age;
}

class PersonBean implements FactoryBean<Person>{

    private String name;
    private int age;

    public void setName(String name)
    {
        this.name=name;
    }
    public void setAge(int age)
    {
        this.age=age;
    }
    @Override
    public Person getObject() throws Exception {
        return new Person(name,age);
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}

@Configuration
class Config
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


public class FactoryBeanConfig {

    public static void main(String[] args) throws Exception {
        PersonBean personBean=new PersonBean();
        Person object = personBean.getObject();
        System.out.println(object);
        Person object1 = personBean.getObject();
        System.out.println(object1==object);
    }
}
