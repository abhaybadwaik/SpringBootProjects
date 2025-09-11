package com.example.DesignPatternPractice;

import com.example.DesignPatternPractice.design.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DesignPatternPracticeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DesignPatternPracticeApplication.class, args);
//		DesignPatternPracticeApplication bean = run.getBean(DesignPatternPracticeApplication.class);
//		DesignPatternPracticeApplication bean1 = run.getBean(DesignPatternPracticeApplication.class);
//		System.out.println(bean==bean1);
		Person person = run.getBean(Person.class);
		System.out.println(person);
		Person person1 = (Person) run.getBean("person");

		System.out.println(person==person1);
	}


}
