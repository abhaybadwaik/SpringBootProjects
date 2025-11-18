package com.example.Spring_CRUD.service;

import com.example.Spring_CRUD.entity.Employee;
import com.example.Spring_CRUD.exceptions.*;
import com.example.Spring_CRUD.repo.EmployeeRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo)
    {
        this.employeeRepo=employeeRepo;
    }

//    public Employee addEmployee(Employee employee)
//    {
//        return employeeRepo.save(employee);
//    }
    public Employee addEmployee(Employee employee) {
    Optional<Employee> existing = employeeRepo.findByName(employee.getName());
    if (existing.isPresent()) {
        throw new EmployeeAlreadyExistsException("Employee with name '" + employee.getName() + "' already exists.");
    }
    return employeeRepo.save(employee);
}

    public List<Employee> addAllEmployees(List<Employee> employees){
        return employeeRepo.saveAll(employees);
    }

    public Employee getEmployeeById(int id)
    {
        return employeeRepo.findById(id).orElseThrow(()->
                new EmployeeNotFoundException("Employee not found with id: " + id));
    }
    public List<Employee> getAllEmployees() {
         return employeeRepo.findAll();
    }


    public String deleteEmployeeById(int id)
    {
         employeeRepo.deleteById(id);
         return "Employee deleted";
    }
    public String deleteAllEmployees() {
        employeeRepo.deleteAll();
        return "All employees have been deleted successfully.";
    }


    public Optional<Employee> updateEmployee(int id, Employee employee)
    {
        Optional<Employee> o = employeeRepo.findById(id).map(emp ->
        {
            if (employee.getName() != null) {
                emp.setName(employee.getName());
            }
            if (employee.getAge() != 0) {
                emp.setAge(employee.getAge());
            }
            emp.setSalary(employee.getSalary());
            employeeRepo.save(emp);
            return emp;
        });
        return o;
    }

    public Employee findEmployeeByName(String name)
    {
       return  employeeRepo.findByName(name).orElseThrow(()->new EmployeeNotFoundByNameException("Employee not found with the name: "+name));
    }

    public Employee addEmp(Employee employee) {
        if (employee.getAge() < 18) {
            throw new InvalidEmployeeAgeException("Employee Should be elder than 18 Years Old");
        }
        return employeeRepo.save(employee);
    }

    public Employee getEmployeeWithHighestSalary() {
        List<Employee> employees = employeeRepo.findAll();

        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found in the database.");
        }
        Employee highestPaid = employees.get(0);

        for (Employee emp : employees) {
            if (emp.getSalary() > highestPaid.getSalary()) {
                highestPaid = emp;
            }
        }
        return highestPaid;
    }

    public Employee addUniqueEmployee(Employee employee) {
        Optional<Employee> existing = employeeRepo.findByName(employee.getName());
        if (existing.isPresent()) {
            throw new RuntimeException("Employee with this name already exists.");
        }
        return employeeRepo.save(employee);
    }

    public Employee updateEmployeeSalaryById(int id, double newSalary) {
        if (newSalary <= 0) {
            throw new InvalidSalaryUpdateException("Salary must be greater than zero");
        }
        Employee emp = employeeRepo.findById(id).orElse(null);
        if (emp != null) {
            emp.setSalary(newSalary);
            return employeeRepo.save(emp);
        }
        return null;
    }
//    public Double getEmployeeSalaryByName(String name) {
//        return employeeRepo.findSalaryByName(name);
//    }



}
