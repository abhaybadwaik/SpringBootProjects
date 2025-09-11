package com.example.SpringSecurityFormat.repository;

import com.example.SpringSecurityFormat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String name);
}
