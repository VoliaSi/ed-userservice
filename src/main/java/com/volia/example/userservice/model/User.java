package com.volia.example.userservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "users") // Avoid SQL keyword conflict
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class User {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    @Column(unique = true)
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

