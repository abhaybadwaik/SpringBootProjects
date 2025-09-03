package com.example.DesignPatternPractice.design;


import lombok.Data;

interface P extends Cloneable
{
    public abstract P clone();
}

@Data
class Student implements P
{
    private String name;
    private int age;
    public Student(String name,int age)
    {
        this.age=age;
        this.name=name;
    }
    @Override
    public Student clone()
    {
        try {
            return (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class Prototype {

    public static void main(String[] args) {
        Student student=new Student("Mahesh",14);
        Student clone = student.clone();
        clone.setAge(20);
        clone.setName("Pulkesh");
        System.out.println(student);
        System.out.println(clone);
        Student clone1 = clone.clone();
        System.out.println(clone==student);
    }

}
