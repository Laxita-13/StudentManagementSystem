package com.example.Day3SMS.controller;

import com.example.Day3SMS.dto.StudentRequestdto;
import com.example.Day3SMS.dto.StudentResponsedto;
import com.example.Day3SMS.model.StudentModel;
import com.example.Day3SMS.service.StudentService;
import com.example.Day3SMS.util.jwtutil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins ="*")
@RestController
public class StudentController {
    private final StudentService service;
    private final jwtutil jwtutil;

    public StudentController(StudentService service,jwtutil jwtutil){
        this.service = service;
        this.jwtutil=jwtutil;
    }
    private void checkToken(String authHeader){
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("Invalid Token");
        }
        String token=authHeader.substring(7);
        jwtutil.validateTokenAndGetEmail(token);
    }
    //create api

    @PostMapping("/students")
    public StudentResponsedto addStudent(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody StudentRequestdto student){
        checkToken(authHeader);
        return service.addStudent(student);
    }

    //display
    @GetMapping("/students")
    public List<StudentResponsedto> getAllStudents(
        @RequestHeader(value="Authorization",required = false)String authHeader) {
        checkToken(authHeader);
        return service.getAllStudents();
    }
    //update
    @PutMapping("/update/{id}")
    public StudentModel updateStudent(
            @PathVariable String id,
            @RequestBody StudentModel student){
        return service.updateStudent(id,student);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id){
        service.deleteStudent(id);
        return "student deleted succesfully";
    }
    @PatchMapping("/students/{id}")
    public StudentResponsedto patchStudent(
            @PathVariable String id,
            @RequestBody StudentRequestdto student) {
        return service.patchStudent(id,student);
    }

}