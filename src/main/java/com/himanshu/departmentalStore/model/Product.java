package com.himanshu.departmentalStore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a product entity in the departmental store system.
 * This entity stores information about products including their name, description, price, expiry date, availability, and count in stock.
 */
@Entity
@Getter
@Setter
public class Product {

    /**
     * The unique identifier for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the product.
     */
    @NotNull(message = "Name Can not be null")
    private String name;

    /**
     * The description of the product.
     */
    private String description;

    /**
     * The price of the product.
     */
    @NotNull(message = "Price Can not be null")
    private BigDecimal price;

    /**
     * The expiry date of the product.
     */
    private LocalDate expiry;

    /**
     * The count of the product in stock.
     */
    private int count;

    /**
     * The availability status of the product.
     */
    private boolean availability;
}
