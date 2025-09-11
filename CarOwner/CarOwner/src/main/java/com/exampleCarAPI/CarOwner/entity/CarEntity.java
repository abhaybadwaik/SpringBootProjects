package com.exampleCarAPI.CarOwner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {


    @SequenceGenerator(name = "car_seq",allocationSize = 20,initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "car_seq")
    @Id
    private Integer id;
    private String brand;
    private String model;
    private String color;
    private long year;

}
