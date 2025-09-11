package com.example.SpringSecurityFormat.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "A_ROLE")
@Data
public class Role {

    @Id
    @SequenceGenerator(name = "role_seq",allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    private Integer id;
    private String name;
}
