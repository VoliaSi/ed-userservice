package com.volia.example.userservice.repository;

import com.volia.example.userservice.model.Student;

public interface StudentRepository extends UserRepository<Student> {
    // No extra methods needed here — inherits all from UserRepository
}
