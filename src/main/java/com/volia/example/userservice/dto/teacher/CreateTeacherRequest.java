package com.volia.example.userservice.dto.teacher;

public record CreateTeacherRequest(
        String name,
        String email,
        String bio
) {}
