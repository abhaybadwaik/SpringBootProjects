package com.example.SpringSecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "B_USERS")
public class User {
    @Id
    @SequenceGenerator(name = "seq_Gen" , allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "seq_gen")

    private Integer userId;
    private String username;
    private String email;
    private String Password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "B_Users_Roles")
    private Set<Role>roles=new HashSet<>();
}
