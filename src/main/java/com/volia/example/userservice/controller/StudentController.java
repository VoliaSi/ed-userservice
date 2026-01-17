package com.volia.example.userservice.controller;

import com.volia.example.userservice.dto.StudentDto;
import com.volia.example.userservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController extends UserController<StudentDto> {

    private final StudentService studentService;

    @Override
    protected StudentService getService() {
        return studentService;
    }
}
