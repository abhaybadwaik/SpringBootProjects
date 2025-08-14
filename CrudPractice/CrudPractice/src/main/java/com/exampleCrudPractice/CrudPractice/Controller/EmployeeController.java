package com.exampleCrudPractice.CrudPractice.Controller;

import com.exampleCrudPractice.CrudPractice.Entity.Employee;
import com.exampleCrudPractice.CrudPractice.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.OK);
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.FOUND);
    }

    @DeleteMapping("/deleteEmployeeById")
    public ResponseEntity<String> deleteEmployee(@RequestParam("employeeId") Integer id) {
        return ResponseEntity.ok(employeeService.deleteEmployeeById(id));
    }
}
