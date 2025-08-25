package org.example;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class HibernateValidatorPractice {
    public static class Usor{
        @NotBlank(message = "Name can never be null okay bro")
        private String name;

        @Email(message = "Email Should be Valid")
        private String email;

        @Min(value = 18, message = "Age should be atleat 18 minimum")
        @Max(value = 50, message = "Age should be less than 50 okay")
        private int age;

        public Usor(String name, String email,int age){
            this.name= name;
            this.email=email;
            this.age=age;
        }
    }

    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Usor use = new Usor("ABHAY","abhay@gmail.com",50);
        //Validate
        Set<ConstraintViolation<Usor>> violations = validator.validate(use);

        for (ConstraintViolation<Usor> violation : violations){
            System.out.println(violation.getPropertyPath()+" : "+ violation.getMessage());
        }
    }
}
