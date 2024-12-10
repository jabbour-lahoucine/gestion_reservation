package com.example.gestion_reservation.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chambre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private Double prix;
    private Boolean disponible;

    @OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    // Getters and Setters
}
