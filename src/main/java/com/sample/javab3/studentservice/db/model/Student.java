package com.sample.javab3.studentservice.db.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "studentsInfoTable")
public class Student {
    @Id
    @GeneratedValue
    private int rollNum;

    @NotBlank(message = "name cannot be blank")
    @Size(min = 3, max = 10, message = "invalid name length")
    @Id
    private String name;

    @Min(value = 0, message = "must be positive")
    @Max(value = 30, message = "age must be below 30")
    private int age;

    @NotBlank(message = "standard cannot be blank")
    @Size(min = 3, max = 10, message = "invalid input length")
    private String standard;

    private String gender;

}
