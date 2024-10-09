package edu.eci.arep.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Properties class
 * Author: Andrés Arias
 */
@Entity
@Getter @Setter
public class Properties {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    private String price;
    private String size;
    private String description;

    /**
     * Default constructor
     */
    public Properties() {
    }

    /**
     * Constructor with parameters
     * @param address address of the property
     * @param price price of the property
     * @param size size of the property
     * @param description description of the property
     */
    public Properties(Long id, String address, String price, String size, String description) {
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
    }
}
