package com.example.gestion_reservation.services;

import com.example.gestion_reservation.entities.Reservation;
import com.example.gestion_reservation.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    // Méthode pour créer une réservation
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Méthode pour mettre à jour une réservation existante
    public Reservation updateReservation(Long id, Reservation reservation) {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);
        if (existingReservation.isPresent()) {
            Reservation updatedReservation = existingReservation.get();
            updatedReservation.setClient(reservation.getClient());
            updatedReservation.setChambre(reservation.getChambre());
            updatedReservation.setDateDebut(reservation.getDateDebut());
            updatedReservation.setDateFin(reservation.getDateFin());
            updatedReservation.setPreferences(reservation.getPreferences());
            return reservationRepository.save(updatedReservation);
        } else {
            throw new RuntimeException("Reservation not found with id: " + id);
        }
    }

    // Méthode pour obtenir une réservation par son ID (modifiée pour retourner une Optional)
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id); // Retourne une Optional<Reservation>
    }

    // Méthode pour obtenir une réservation par son ID (ancienne version qui retourne ResponseEntity)
    public Reservation findReservationById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.orElse(null);
    }

    // Méthode pour obtenir toutes les réservations
    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    // Méthode pour supprimer une réservation par son ID
    public void deleteReservation(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            reservationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Reservation not found with id: " + id);
        }
    }
}
