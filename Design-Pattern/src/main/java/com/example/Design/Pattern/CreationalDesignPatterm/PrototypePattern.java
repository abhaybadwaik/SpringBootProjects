package com.example.Design.Pattern.CreationalDesignPatterm;


import lombok.Data;

interface P extends Cloneable{
    public abstract P clone();
}
@Data
class Student implements P{
    private  String name;
    private int age;

    public Student(String name,int age){
        this.name=name;
        this.age=age;
    }
    @Override
    public Student clone() {
      try {
          return (Student) super.clone();
      } catch (CloneNotSupportedException e) {
          throw new RuntimeException(e);
      }
    }
}


public class PrototypePattern {
    public static void main(String[] args) {
        Student student = new Student("Abhay",23);
        Student clone = student.clone();
        clone.setName("Sumith");
        clone.setAge(25);
        System.out.println(student);
        System.out.println(clone);
        System.out.println(student==clone);
    }
}

