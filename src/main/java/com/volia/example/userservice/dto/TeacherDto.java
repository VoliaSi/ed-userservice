package com.volia.example.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TeacherDto(
        Long id,
        @NotBlank String name,
        @Email @NotBlank String email,
        String bio
) implements UserDto {}


