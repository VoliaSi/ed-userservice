package com.volia.example.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentDto(
        Long id,
        @NotBlank String name,
        @Email @NotBlank String email,
        String university
) implements UserDto {}
