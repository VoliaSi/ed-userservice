package com.volia.example.userservice.dto.student;

public record CreateStudentRequest(
        String name,
        String email,
        String university
) {}
