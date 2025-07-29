package com.volia.example.userservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class StudentDTO {
    Long id;
    String name;
    String email;
    String university;
}
