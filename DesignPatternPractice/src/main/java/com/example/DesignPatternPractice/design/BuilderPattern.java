package com.example.DesignPatternPractice.design;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
class Employee
{
    private String name;
    private String branch;
    private String age;

    public Employee(Builder builder) {
        this.name=builder.name;
        this.age=builder.age;
        this.branch=builder.branch;
    }

    public static class Builder
    {
        private String name;
        private String branch;
        private String age;

        public Builder name(String name)
        {

            this.name=name;
            return this;
        }
        public Builder age(String age)
        {
            this.age=age;
            return this;
        }
        public Builder branch(String branch)
        {
            this.branch=branch;
            return this;
        }

        public Employee build()
        {
            return new Employee(this);
        }

    }

}
public class BuilderPattern {
    public static void main(String[] args) {

        Employee build = new Employee.Builder()
                .age("21")
                .name("Pulkesh")
                .branch("Mech")
                .build();
        System.out.println(build);
    }
}


