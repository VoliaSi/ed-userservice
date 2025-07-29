package com.volia.example.userservice.repository;

import com.volia.example.userservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>
{}