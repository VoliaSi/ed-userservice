package com.volia.example.userservice.repository;

import com.volia.example.userservice.model.Teacher;

public interface TeacherRepository extends UserRepository<Teacher> {
    // Inherits findByEmail, existsByEmail, etc.
}
