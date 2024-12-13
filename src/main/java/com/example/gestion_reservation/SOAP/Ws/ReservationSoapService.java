package com.example.gestion_reservation.SOAP.Ws;

import com.example.gestion_reservation.dto.ReservationRequest;
import com.example.gestion_reservation.entities.Chambre;
import com.example.gestion_reservation.entities.Client;
import com.example.gestion_reservation.entities.Reservation;
import com.example.gestion_reservation.repositories.ChambreRepository;
import com.example.gestion_reservation.repositories.ClientRepository;
import com.example.gestion_reservation.repositories.ReservationRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@WebService(serviceName = "ReservationSoapService")
public class ReservationSoapService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ChambreRepository chambreRepository;

    @WebMethod
    public Reservation createReservation(@WebParam(name = "request") ReservationRequest request) {

        if (request.getClientId() == null || request.getChambreId() == null) {
            throw new IllegalArgumentException("Client ID and Chambre ID must be provided");
        }


        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + request.getClientId()));
        Chambre chambre = chambreRepository.findById(request.getChambreId())
                .orElseThrow(() -> new RuntimeException("Chambre not found with ID: " + request.getChambreId()));


        Reservation reservation = new Reservation();
        reservation.setDateDebut(request.getDateDebut());
        reservation.setDateFin(request.getDateFin());
        reservation.setPreferences(request.getPreferences());
        reservation.setClient(client);
        reservation.setChambre(chambre);


        return reservationRepository.save(reservation);
    }

    @WebMethod
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @WebMethod
    public Reservation getReservationById(@WebParam(name = "id") Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with ID: " + id));
    }

    @WebMethod
    public void deleteReservation(@WebParam(name = "id") Long id) {
        reservationRepository.deleteById(id);
    }

    @WebMethod
    public Reservation updateReservation(@WebParam(name = "id") Long id, @WebParam(name = "request") ReservationRequest request) {
        return reservationRepository.findById(id).map(reservation -> {
            reservation.setDateDebut(request.getDateDebut());
            reservation.setDateFin(request.getDateFin());
            reservation.setPreferences(request.getPreferences());

            if (request.getClientId() != null) {
                Client client = clientRepository.findById(request.getClientId())
                        .orElseThrow(() -> new RuntimeException("Client not found with ID: " + request.getClientId()));
                reservation.setClient(client);
            }

            if (request.getChambreId() != null) {
                Chambre chambre = chambreRepository.findById(request.getChambreId())
                        .orElseThrow(() -> new RuntimeException("Chambre not found with ID: " + request.getChambreId()));
                reservation.setChambre(chambre);
            }

            return reservationRepository.save(reservation);
        }).orElseThrow(() -> new RuntimeException("Reservation not found with ID: " + id));
    }
}
