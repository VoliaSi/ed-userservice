package com.volia.example.userservice.dto.teacher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTeacherRequest(

        @NotBlank String name,
        @Email @NotBlank String email,
        @Size(min = 8) String password,
        String bio
) {}

