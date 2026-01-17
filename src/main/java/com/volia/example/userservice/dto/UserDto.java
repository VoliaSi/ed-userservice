package com.volia.example.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public sealed interface UserDto permits StudentDto, TeacherDto {
    Long id();
    @NotBlank String name();
    @Email @NotBlank String email();
}