package com.volia.example.userservice.dto.teacher;

public record UpdateTeacherRequest(
        String name,
        String email,
        String bio
) {}

