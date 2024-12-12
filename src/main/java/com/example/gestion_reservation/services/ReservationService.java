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

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

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
        }
        throw new RuntimeException("Reservation not found with id: " + id);
    }

    public Optional<Reservation> findReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }






}
