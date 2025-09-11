package com.example.DesignPatternPractice.design;

import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

@Setter
public class PersonBean implements FactoryBean<Person> {

    private String name;
    private int age;

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
        return false;
    }
}
