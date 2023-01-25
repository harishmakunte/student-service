package com.sample.javab3.studentservice.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class StudentControllerAdvice {


    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<Object> handleNouserFound(NoUserFoundException ex, WebRequest req) {

        log.error("NoUserFoundException occured for request- {}", req);

        return new ResponseEntity<>("no student with the given rollNumber", HttpStatus.OK);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleConstraintVoilations(MethodArgumentNotValidException ex, WebRequest req) {

        Map<String, String> errorMap = new HashMap<>();
        var errors = ex.getBindingResult().getAllErrors();
        ex.getBindingResult().getFieldErrors().forEach(objectError -> {
            var field = objectError.getField();
            var message = objectError.getDefaultMessage();
            errorMap.put(field, message);
        });

        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintVoilations(ConstraintViolationException ex, WebRequest req) {

        Map<Object, String> errorMap = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
           var value = violation.getPropertyPath();
           var message = violation.getMessage();
           errorMap.put(value, message);

        });

        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);

    }


}
