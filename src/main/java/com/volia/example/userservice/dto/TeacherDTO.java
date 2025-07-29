package com.volia.example.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true) // adds no-arg constructor with default values, helps MapStruct
public class TeacherDTO {
    Long id;
    String name;
    String email;
    String bio;
}
