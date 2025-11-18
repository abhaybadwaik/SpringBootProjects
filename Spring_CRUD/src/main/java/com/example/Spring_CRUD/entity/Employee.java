package com.example.Spring_CRUD.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @SequenceGenerator(name = "employee_seq",allocationSize = 20,initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "employee_seq")
    @Id
    private Integer id;
    private String name;
    private double salary;
    private int age;

}
