package com.exampleCrudPractice.CrudPractice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @SequenceGenerator(name = "employee_seq",allocationSize = 20,initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "employee_seq")

    @Id
    private Integer id;
    private String name;
    private double salary;
    private int age;
}
