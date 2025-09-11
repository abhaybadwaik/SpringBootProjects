package com.exampleMappingAPI.MappingAPI.repository;

import com.exampleMappingAPI.MappingAPI.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
