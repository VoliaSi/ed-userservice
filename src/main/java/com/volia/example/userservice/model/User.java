package com.volia.example.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    @ToString.Include
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    @ToString.Include
    private String email;
}


//CONSTRAINTS ON USING DATA
// Add a required-args constructor (not useful for JPA)
//        Potential Issues with @Data on JPA entities
//equals() / hashCode():
//
//These methods are generated based on all fields, which can cause issues for:
//
//Lazy-loaded relationships
//
//Entity identity (e.g. comparing entities before id is set)
//
//Best practice: Use @EqualsAndHashCode(onlyExplicitlyIncluded = true) and include only id.
//
//toString():
//
//It can trigger lazy loading and stack overflows if there are bidirectional relationships.
//
//Better to use @ToString(onlyExplicitlyIncluded = true) or avoid @Data.
//

