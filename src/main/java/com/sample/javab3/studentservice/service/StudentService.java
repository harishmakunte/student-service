package com.sample.javab3.studentservice.service;

import com.sample.javab3.studentservice.db.model.Student;
import com.sample.javab3.studentservice.db.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> fetchAllStudents (){
        System.out.println("inside fetchAllStudents");
        return studentRepo.findAll();

    }

}
