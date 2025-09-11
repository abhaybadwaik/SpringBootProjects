package com.exampleMappingAPI.MappingAPI.service;


import com.exampleMappingAPI.MappingAPI.entity.Employees;
import com.exampleMappingAPI.MappingAPI.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    public final EmployeeRepository employeeRepository2;

    public EmployeeService(EmployeeRepository employeeRepository2){
        this.employeeRepository2 = employeeRepository2;
    }

    public List<Employees> getAllEmployees() {
        return employeeRepository2.findAll();
    }

    public Employees saveEmployees(Employees employees){
        return employeeRepository2.save(employees);
    }

    public Employees getEmployeeById(Integer id) {
        return employeeRepository2.findById(id).orElse(null);
    }

    public void deleteEmployee(Integer id) {
        employeeRepository2.deleteById(id);
    }

    public Employees updateEmployee(Integer id, Employees updatedEmployee) {
        Employees existingEmployee = employeeRepository2.findById(id).orElse(null);

        if (existingEmployee != null) {
            if (updatedEmployee.getName() != null) {
                existingEmployee.setName(updatedEmployee.getName());
            }
            if (updatedEmployee.getDepartment() != null) {
                existingEmployee.setDepartment(updatedEmployee.getDepartment());
            }
            return employeeRepository2.save(existingEmployee);
        } else {
            return null;
        }
    }

}
