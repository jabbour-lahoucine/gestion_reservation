package com.example.gestion_reservation.controller;

import com.example.gestion_reservation.entities.Chambre;
import com.example.gestion_reservation.entities.Reservation;
import com.example.gestion_reservation.repositories.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ChamberController {
    @Autowired
    private ChambreRepository chambreRepository;

    // Query to fetch all chambres
    @QueryMapping
    public List<Chambre> chambres() {
        return chambreRepository.findAll();
    }

    // Query to fetch a chambre by ID
    @QueryMapping
    public Chambre chambre(@Argument Long id) {
        return chambreRepository.findById(id).orElse(null);
    }

    // Fetch reservations for a specific chambre
    @QueryMapping
    public List<Reservation> reservationsForChambre(@Argument Long chambreId) {
        Chambre chambre = chambreRepository.findById(chambreId).orElseThrow(() -> new RuntimeException("Chambre not found"));
        return chambre.getReservations();
    }

    // Mutation to create a new chambre
    @MutationMapping
    public Chambre createChambre(
            @Argument String type,
            @Argument Double prix,  // We will convert this to BigDecimal
            @Argument Boolean disponible
    ) {
        Chambre chambre = new Chambre();
        chambre.setType(type);
        chambre.setPrix(prix);  // Convert Double to BigDecimal
        chambre.setDisponible(disponible);
        return chambreRepository.save(chambre);
    }

    // Mutation to update an existing chambre
    @MutationMapping
    public Chambre updateChambre(
            @Argument Long id,
            @Argument String type,
            @Argument Double prix,  // We will convert this to BigDecimal
            @Argument Boolean disponible
    ) {
        Chambre chambre = chambreRepository.findById(id).orElseThrow(() -> new RuntimeException("Chambre not found"));
        if (type != null) chambre.setType(type);
        if (prix != null) chambre.setPrix(prix);
        if (disponible != null) chambre.setDisponible(disponible);
        return chambreRepository.save(chambre);
    }

    // Mutation to delete a chambre
    @MutationMapping
    public Boolean deleteChambre(@Argument Long id) {
        if (chambreRepository.existsById(id)) {
            chambreRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
