package com.exampleMappingAPI.MappingAPI.controller;

import com.exampleMappingAPI.MappingAPI.entity.Employees;
import com.exampleMappingAPI.MappingAPI.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employees> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employees getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employees createEmployee(@RequestBody Employees employee) {
        return employeeService.saveEmployees(employee);
    }

    @PutMapping("/{id}")
    public Employees updateEmployee(@PathVariable Integer id, @RequestBody Employees employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }
}

