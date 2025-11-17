package com.example.SpringSecurityFormat.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="A_USERS")
public class User {
    @Id
    @SequenceGenerator(name = "user_seq",allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Integer userId;
    private String username;
    private String email;
    private String Password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles")
    private Set<Role>roles=new HashSet<>();

}
