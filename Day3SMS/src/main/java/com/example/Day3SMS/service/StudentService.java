package com.example.Day3SMS.service;

import com.example.Day3SMS.dto.StudentRequestdto;
import com.example.Day3SMS.dto.StudentResponsedto;
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
    public StudentResponsedto addStudent(StudentRequestdto dto){
        StudentModel student=new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());
        StudentModel saved=repository.save(student);
        return new StudentResponsedto(
            saved.getId(),
            saved.getName(),
            saved.getAge(),
            saved.getEmail()
        );
    }
    public List<StudentResponsedto> getAllStudents(){
        return repository.findAll()
                .stream()
                .map( s -> new StudentResponsedto(
                        s.getId(),
                        s.getName(),
                        s.getAge(),
                        s.getEmail()
                )).toList();
    }
    //public List<StudentModel> getAllStudents(){
    //    return repository.findAll();
    //}
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