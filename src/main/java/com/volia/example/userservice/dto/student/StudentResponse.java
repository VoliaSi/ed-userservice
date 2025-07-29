package com.volia.example.userservice.dto.student;

public record StudentResponse(
        Long id,
        String name,
        String email,
        String university
) {}

