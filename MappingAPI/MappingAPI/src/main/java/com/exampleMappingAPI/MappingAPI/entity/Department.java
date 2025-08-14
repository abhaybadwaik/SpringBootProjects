package com.exampleMappingAPI.MappingAPI.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @SequenceGenerator(name = "sequence1")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence1")
    private Integer id;

    private String deptName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<Employees> employees;
}