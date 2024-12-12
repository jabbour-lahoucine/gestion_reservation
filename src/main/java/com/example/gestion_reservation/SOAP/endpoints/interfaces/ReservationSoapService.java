package com.example.gestion_reservation.SOAP.endpoints.interfaces;


import com.example.gestion_reservation.SOAP.entities.ReservationRequest;
import com.example.gestion_reservation.SOAP.entities.ReservationResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface ReservationSoapService {
    @WebMethod
    ReservationResponse createReservation(ReservationRequest request);

    @WebMethod
    ReservationResponse getReservationById(ReservationRequest request);

    @WebMethod
    ReservationResponse updateReservation(ReservationRequest request);

    @WebMethod
    ReservationResponse deleteReservation(ReservationRequest request);
}
