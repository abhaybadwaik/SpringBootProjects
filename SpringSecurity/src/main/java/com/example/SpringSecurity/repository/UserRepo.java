package com.example.SpringSecurity.repository;

import com.example.SpringSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

   Optional<User> findByUsername(String name);
}
