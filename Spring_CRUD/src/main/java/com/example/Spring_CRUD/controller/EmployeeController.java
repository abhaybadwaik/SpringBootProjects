package com.example.Spring_CRUD.controller;

import com.example.Spring_CRUD.entity.Employee;
import com.example.Spring_CRUD.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)
    {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.OK);
    }

    @PostMapping("/addAllEmployees")
    public ResponseEntity<List<Employee>> addAllEmployees(@RequestBody List<Employee> employees){
        return ResponseEntity.ok(employeeService.addAllEmployees(employees));
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.FOUND);
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
       return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.FOUND);
     }

    public EmployeeController(EmployeeService employeeService)
    {
        this.employeeService=employeeService;
    }

    @DeleteMapping("/deleteEmployeeById")
    public ResponseEntity<String> deleteEmployee(@RequestParam("employeeId") Integer id)
    {
        return ResponseEntity.ok(employeeService.deleteEmployeeById(id));
    }

    @DeleteMapping("/deleteAll")
    public String deleteAllEmployees() {
        return employeeService.deleteAllEmployees();
    }

    @PutMapping("/updateEmployeeById/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Integer id,@RequestBody Employee employee)
    {
        return ResponseEntity.ok(employeeService.updateEmployee(id,employee).get());
    }

    @GetMapping("/getEmployeeByName/{name}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable String name)
    {
       return new ResponseEntity<>( employeeService.findEmployeeByName(name),HttpStatus.ACCEPTED);
    }

    @PostMapping("/addAge")
    public ResponseEntity<Employee> addEmp(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.addEmp(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/highest-salary")
    public Employee getHighestSalaryEmployee() {
        return employeeService.getEmployeeWithHighestSalary();
    }

    @PostMapping("/addUniqueEmployee")
    public Employee addUniqueEmployee(@RequestBody Employee employee) {
        return employeeService.addUniqueEmployee(employee);
    }

    @PutMapping("/update-salary/{id}")
    public Employee updateSalary(@PathVariable int id, @RequestParam double salary) {
        return employeeService.updateEmployeeSalaryById(id, salary);
    }

}
