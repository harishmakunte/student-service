package com.sample.javab3.studentservice.controller;

import com.sample.javab3.studentservice.db.model.Student;
import com.sample.javab3.studentservice.db.repository.StudentRepository;
import com.sample.javab3.studentservice.exception.NoUserFoundException;
import com.sample.javab3.studentservice.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/v1")
@Validated
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private StudentService studentService;

    @GetMapping("/students/{rollNum}")
    public ResponseEntity<Object> fetchStudentByID(@PathVariable @Min(1)
                                                       @Max(100) int rollNum) throws NoUserFoundException {

        log.info("fetchStudentByID called with rollnumber - {}", rollNum);
        var response = studentRepo.findById(rollNum);

        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            throw new NoUserFoundException("no Student for the given roll number");
        }
    }

    @GetMapping("/students/{rollNum}/{name}")
    public ResponseEntity<Object> fetchStudentByID(@PathVariable int rollNum, @PathVariable @NotBlank @Size(min = 3) String name) {
        System.out.println("inside fetchStudentByID");

        var response = studentRepo.findStudentCustom(rollNum,name);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/students")
    public ResponseEntity<Object> fetchAllStudents() {
        System.out.println("inside fetchAllStudents");
        return new ResponseEntity<>(studentService.fetchAllStudents(), HttpStatus.OK);
    }

    @PostMapping(value = "/students")
    public ResponseEntity<Object> saveStudent(@RequestBody @Valid Student obj) {
        log.info("inside post student method");
        var ret = studentRepo.save(obj);
        return new ResponseEntity<>("Student stored into DB", HttpStatus.OK);

    }

    @PutMapping("/students")
    public ResponseEntity<Object> updateStudent(@RequestBody Student obj) {
        var ret = studentRepo.save(obj);
        return new ResponseEntity<>("Student object updated", HttpStatus.OK);

    }


    @DeleteMapping("/students/{rollNum}")
    public ResponseEntity<Object> deleteByID(@PathVariable int rollNum) {
        studentRepo.deleteById(rollNum);
        return new ResponseEntity<>("student deleted", HttpStatus.OK);
    }


}
