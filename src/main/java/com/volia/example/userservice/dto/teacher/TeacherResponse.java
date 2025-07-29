package com.volia.example.userservice.dto.teacher;

public record TeacherResponse(
        Long id,
        String name,
        String email,
        String bio
) {}

