package edu.eci.arep.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * User class
 * Author: Andrés Arias
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String lastName;
    private String password;

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * Constructor with parameters for user
     * @param id id of the user
     * @param name name of the user
     * @param lastName last name of the user
     * @param email email of the user
     * @param password password of the user
     */
    public User(Long id, String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
