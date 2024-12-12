package com.example.gestion_reservation.SOAP.endpoints.interfaces;


import com.example.gestion_reservation.SOAP.entities.ChambreRequest;
import com.example.gestion_reservation.SOAP.entities.ChambreResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface ChambreSoapService {
    @WebMethod
    ChambreResponse createChambre(ChambreRequest request);

    @WebMethod
    ChambreResponse getChambreById(ChambreRequest request);

    @WebMethod
    ChambreResponse updateChambre(ChambreRequest request);

    @WebMethod
    ChambreResponse deleteChambre(ChambreRequest request);
}
