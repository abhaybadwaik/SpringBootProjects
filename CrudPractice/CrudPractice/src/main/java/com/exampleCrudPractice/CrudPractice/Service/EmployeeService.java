package com.exampleCrudPractice.CrudPractice.Service;

import com.exampleCrudPractice.CrudPractice.Entity.Employee;
import com.exampleCrudPractice.CrudPractice.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService{

    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee addEmployee(Employee employee){
        return employeeRepo.save(employee);
    }
    public Employee getEmployeeById(int id){
        return employeeRepo.findById(id).get();

    }
    public String deleteEmployeeById(int id){
        employeeRepo.deleteById(id);
        return "Succefully Delted";
    }


}

//    public Employee updateEmployeeById(int id,Employee employee){
//        Optional<Employee> emp = employeeRepo.findById(id);
//        if(emp.isEmpty()){
//            return new Employee();
//        }
//        emp.map(employee1 ->{
//           if(employee1.getName()!=null){
//               employee1.setName(employee.getName());
//           }
//           if (employee1.getAge()!=0){
//               employee1.setAge(employee.getAge());
//           }
//           if (employee1.getSalary()!=0.0){
//               employee1.setSalary(employee.getSalary());
//           }
//           return employee1;
//        });
//        return employee;
//    }
//}
