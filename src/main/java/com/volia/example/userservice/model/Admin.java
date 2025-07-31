package com.volia.example.userservice.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    // You can add admin-specific fields here if needed

    // For now, no extra fields needed
}

