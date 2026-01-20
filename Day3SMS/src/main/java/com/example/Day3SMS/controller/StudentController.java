package com.example.Day3SMS.controller;

import com.example.Day3SMS.model.StudentModel;
import com.example.Day3SMS.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Provider;

@RestController
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service){
        this.service=service;

    }
    // create function API
    @PostMapping("/add-student")
    public StudentModel addstudent(@RequestBody StudentModel student){
        return service.addStudent(student);
    }
}
