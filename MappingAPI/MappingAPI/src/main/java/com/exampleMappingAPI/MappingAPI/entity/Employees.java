package com.exampleMappingAPI.MappingAPI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employees {

    @Id
    @SequenceGenerator(name = "sequence2")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence2")
    private Integer id;

    private String name;

    private String role;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "department_id")
    private Department department;
}