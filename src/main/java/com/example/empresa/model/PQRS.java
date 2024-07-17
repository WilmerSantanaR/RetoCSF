package com.example.empresa.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@Table(name = "pqrs")
public class PQRS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // Peticion, Queja, Reclamo, Sugerencia

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private String status; // Pendiente, En proceso, Resuelta, Cancelada

    private String comment;


    public PQRS() {
    }

    public PQRS(String type, String description, User user, String status, String comment) {
        this.type = type;
        this.description = description;
        this.user = user;
        this.status = status;
        this.comment = comment;
        this.createdAt = LocalDateTime.now();
    }

    public PQRS(String type, String description, Long userId, String status, String comment) {
        this.type = type;
        this.description = description;
        this.user = new User();
        this.user.setId(userId);
        this.status = status;
        this.comment = comment;
    }
}
