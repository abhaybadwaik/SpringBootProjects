package com.example.DesignPatternPractice.design;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Animal
{
    private String name;
    private int age;
}

class AnimalBean extends AbstractFactoryBean<Animal>
{
    @Override
    public Class<?> getObjectType() {
        return Animal.class;
    }

    @Override
    protected Animal createInstance() throws Exception {
        return new Animal("Tiger",21);
    }
}

@Configuration
class AnimalConfig
{
    @Bean
    public AnimalBean animal()
    {
        return new AnimalBean();
    }
}
public class AbstarctFactoryBeanExample {


    public static void main(String[] args) {

    }
}
