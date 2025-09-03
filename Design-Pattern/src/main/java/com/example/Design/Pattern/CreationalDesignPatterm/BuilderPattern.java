package com.example.Design.Pattern.CreationalDesignPatterm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
class Employee{
    private String name;
    private String email;
    private String age;

    public Employee(Builder builder){
        this.name=builder.name;
        this.age=builder.age;
        this.email=builder.email;
    }

    public static class Builder{
        private String name;
        private String email;
        private String age;

        public Builder name(String name){
            this.name=name;
            return this;
        }
        public Builder email(String email){
            this.email=email;
            return this;
        }
        public Builder age(String age){
            this.age=age;
            return this;
        }

        public Employee build(){
            return new Employee(this);
        }
    }
}
public class BuilderPattern {
    public static void main(String[] args) {
        Employee build = new Employee.Builder()
                .age("21")
                .email("abhay@gmail.com")
                .name("ABHAY")
                .build();
        System.out.println(build);

    }

}
