package com.example.SpringSecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "B_ROLES")
public class Role {
    @Id
    @SequenceGenerator(name = "role_seq",allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "role_seq")
    private Integer id;
    private String name;

}
