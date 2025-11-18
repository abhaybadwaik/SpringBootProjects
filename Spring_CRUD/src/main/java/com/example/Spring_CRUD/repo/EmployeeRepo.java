package com.example.Spring_CRUD.repo;

import com.example.Spring_CRUD.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByName(String name);

//    @Query("SELECT e.salary FROM Employee e WHERE e.id = :id")
//    Double findSalaryById(int id);

//    @Query("SELECT e.salary FROM Employee e WHERE e.name = :name")
//    Double findSalaryByName(String name);
}
