package com.volia.example.userservice.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@Getter
@Setter
@NoArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password; // шыфраваць
}
