package com.volia.example.userservice.repository;

import com.volia.example.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
    Optional<T> findByEmail(String email);
    boolean existsByEmail(String email);
}
