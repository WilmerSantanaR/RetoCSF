package com.example.empresa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private int stock;
    private String imageUrl; // Nuevo campo para la URL de la imagen

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Constructor que acepta un objeto Category
    public Product(String name, String description, Double price, int stock, Category category, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.imageUrl = imageUrl; // Inicializar el campo imageUrl
    }
}
