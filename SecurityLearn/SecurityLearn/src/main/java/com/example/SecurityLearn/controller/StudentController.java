package com.example.SecurityLearn.controller;

import com.example.SecurityLearn.Fields.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Abhay",45),
            new Student(2,"RAHUL",47)
    ));


    @GetMapping("/student")
    public List <Student> getStudent(){
        return students;
    }

    @GetMapping("csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }



    @PostMapping("/student")
    public Student addStudents(@RequestBody Student student){
        students.add(student);
        return student;
    }

}
