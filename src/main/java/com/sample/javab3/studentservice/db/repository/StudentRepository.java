package com.sample.javab3.studentservice.db.repository;

import com.sample.javab3.studentservice.db.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "Select s from studentsInfoTable s where s.rollNum = :roll and s.name= :nam")
    public Student findStudentCustom(int roll, String nam);


}
