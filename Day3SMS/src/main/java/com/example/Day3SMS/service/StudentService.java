package com.example.Day3SMS.service;

import com.example.Day3SMS.model.StudentModel;
import com.example.Day3SMS.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }
    //create
    public StudentModel addStudent(StudentModel student){
        return repository.save(student);
    }

    public List<StudentModel> getAllStudents(){
        return repository.findAll();
    }
    public StudentModel updateStudent(String id,StudentModel student){
        StudentModel  existingStudent = repository.findById(id).
                orElseThrow(() -> new RuntimeException("No student found"));

        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());

        return repository.save(existingStudent);


    }
    public void deleteStudent(String id){
        StudentModel student  = repository.findById(id).
                orElseThrow(() -> new RuntimeException("No student found"));
        repository.delete(student);
    }

}