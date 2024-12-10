package com.example.gestion_reservation.repositories;

import com.example.gestion_reservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface reservationRepository extends JpaRepository<Reservation, Long> {
}
