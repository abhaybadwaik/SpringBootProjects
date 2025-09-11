package com.exampleMappingAPI.MappingAPI.repository;

import com.exampleMappingAPI.MappingAPI.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees,Integer> {
}
