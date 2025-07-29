package com.volia.example.userservice.dto.student;

public record UpdateStudentRequest(
        String name,
        String email,
        String university
) {}

