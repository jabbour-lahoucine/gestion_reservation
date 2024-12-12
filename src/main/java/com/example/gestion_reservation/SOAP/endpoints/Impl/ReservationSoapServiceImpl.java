package com.example.gestion_reservation.SOAP.endpoints.Impl;

import com.example.gestion_reservation.SOAP.endpoints.interfaces.ReservationSoapService;
import com.example.gestion_reservation.SOAP.entities.ReservationRequest;
import com.example.gestion_reservation.SOAP.entities.ReservationResponse;
import com.example.gestion_reservation.entities.Reservation;
import com.example.gestion_reservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;

public class ReservationSoapServiceImpl implements ReservationSoapService {
    private final ReservationService reservationService;

    @Autowired
    public ReservationSoapServiceImpl(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public ReservationResponse createReservation(ReservationRequest request) {
        // Créer une nouvelle réservation
        Reservation reservation = new Reservation();
        reservation.setClient(request.getClient());
        reservation.setChambre(request.getChambre());
        reservation.setDateDebut(request.getDateDebut());
        reservation.setDateFin(request.getDateFin());

        // Utiliser un String pour les préférences
        reservation.setPreferences(request.getPreferences());

        // Sauvegarder la réservation dans le service
        Reservation savedReservation = reservationService.createReservation(reservation);

        // Préparer la réponse
        ReservationResponse response = new ReservationResponse();
        response.setReservationId(savedReservation.getId());
        response.setClient(savedReservation.getClient());
        response.setChambre(savedReservation.getChambre());
        response.setDateDebut(savedReservation.getDateDebut());
        response.setDateFin(savedReservation.getDateFin());
        response.setPreferences(savedReservation.getPreferences());

        return response;
    }

    @Override
    public ReservationResponse getReservationById(ReservationRequest request) {
        // Récupérer la réservation par ID
        Reservation reservation = reservationService.getReservationById(request.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        // Préparer la réponse
        ReservationResponse response = new ReservationResponse();
        response.setReservationId(reservation.getId());
        response.setClient(reservation.getClient());
        response.setChambre(reservation.getChambre());
        response.setDateDebut(reservation.getDateDebut());
        response.setDateFin(reservation.getDateFin());
        response.setPreferences(reservation.getPreferences());

        return response;
    }

    @Override
    public ReservationResponse updateReservation(ReservationRequest request) {
        // Récupérer la réservation existante
        Reservation reservation = reservationService.getReservationById(request.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        // Mettre à jour les informations de la réservation
        reservation.setClient(request.getClient());
        reservation.setChambre(request.getChambre());
        reservation.setDateDebut(request.getDateDebut());
        reservation.setDateFin(request.getDateFin());

        // Utiliser un String pour les préférences
        reservation.setPreferences(request.getPreferences());

        // Sauvegarder la réservation mise à jour
        Reservation updatedReservation = reservationService.createReservation(reservation);

        // Préparer la réponse
        ReservationResponse response = new ReservationResponse();
        response.setReservationId(updatedReservation.getId());
        response.setClient(updatedReservation.getClient());
        response.setChambre(updatedReservation.getChambre());
        response.setDateDebut(updatedReservation.getDateDebut());
        response.setDateFin(updatedReservation.getDateFin());
        response.setPreferences(updatedReservation.getPreferences());

        return response;
    }

    @Override
    public ReservationResponse deleteReservation(ReservationRequest request) {
        // Récupérer la réservation par ID
        Reservation reservation = reservationService.getReservationById(request.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        // Supprimer la réservation
        reservationService.deleteReservation(reservation.getId());

        // Préparer la réponse
        ReservationResponse response = new ReservationResponse();
        response.setReservationId(reservation.getId());

        return response;
    }
}
