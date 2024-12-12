package com.example.gestion_reservation.controller;

import com.example.gestion_reservation.entities.Chambre;
import com.example.gestion_reservation.entities.Client;
import com.example.gestion_reservation.entities.Reservation;
import com.example.gestion_reservation.repositories.ChambreRepository;
import com.example.gestion_reservation.repositories.ClientRepository;
import com.example.gestion_reservation.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    // Query to fetch all reservations
    @QueryMapping
    public List<Reservation> reservations() {
        return reservationRepository.findAll();
    }

    // Query to fetch a reservation by ID
    @QueryMapping
    public Reservation reservation(@Argument Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    // Mutation to create a new reservation
    @MutationMapping
    public Reservation createReservation(@Argument Long clientId, @Argument Long chambreId,
                                         @Argument String dateDebut, @Argument String dateFin,
                                         @Argument String preferences) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new RuntimeException("Chambre not found"));

        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setChambre(chambre);
        reservation.setDateDebut(LocalDate.parse(dateDebut));  // Utilisation de LocalDate
        reservation.setDateFin(LocalDate.parse(dateFin));      // Utilisation de LocalDate
        reservation.setPreferences(preferences);

        return reservationRepository.save(reservation);
    }

    // Mutation to update an existing reservation
    @MutationMapping
    public Reservation updateReservation(@Argument Long id,
                                         @Argument Long clientId,
                                         @Argument Long chambreId,
                                         @Argument String dateDebut,
                                         @Argument String dateFin,
                                         @Argument String preferences) {
        // Retrieve the existing reservation from the database
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        // Retrieve the existing client from the database
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Retrieve the existing chambre from the database
        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new RuntimeException("Chambre not found"));

        // Update the reservation fields with the new values
        reservation.setClient(client);
        reservation.setChambre(chambre);
        reservation.setDateDebut(LocalDate.parse(dateDebut));  // Utilisation de LocalDate
        reservation.setDateFin(LocalDate.parse(dateFin));      // Utilisation de LocalDate
        reservation.setPreferences(preferences);

        // Save the updated reservation to the repository
        return reservationRepository.save(reservation);
    }

    // Mutation to delete a reservation
    @MutationMapping
    public Boolean deleteReservation(@Argument Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
