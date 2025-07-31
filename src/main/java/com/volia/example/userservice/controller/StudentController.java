package com.volia.example.userservice.controller;

import com.volia.example.userservice.dto.student.CreateStudentRequest;
import com.volia.example.userservice.dto.student.StudentResponse;
import com.volia.example.userservice.dto.student.UpdateStudentRequest;
import com.volia.example.userservice.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public StudentResponse createStudent(@RequestBody @Valid CreateStudentRequest dto) {
        log.info("Received request to create student with email: {}", dto.email());
        StudentResponse response = studentService.create(dto);
        log.info("Successfully created student with ID: {}", response.id());
        return response;
    }

    @PutMapping("/{id}")
    public StudentResponse updateStudent(@PathVariable Long id, @RequestBody @Valid UpdateStudentRequest dto) {
        log.info("Updating student with ID: {}", id);
        StudentResponse response = studentService.update(id, dto);
        log.info("Updated student with ID: {}", id);
        return response;
    }

    @GetMapping("/{id}")
    public StudentResponse getStudent(@PathVariable Long id) {
        log.info("Fetching student with ID: {}", id);
        return studentService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        log.warn("Deleting student with ID: {}", id);
        studentService.delete(id);
        log.info("Deleted student with ID: {}", id);
    }
}
