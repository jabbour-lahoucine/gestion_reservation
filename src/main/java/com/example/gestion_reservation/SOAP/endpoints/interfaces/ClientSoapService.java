package com.example.gestion_reservation.SOAP.endpoints.interfaces;


import com.example.gestion_reservation.SOAP.entities.ClientRequest;
import com.example.gestion_reservation.SOAP.entities.ClientResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface ClientSoapService {
    @WebMethod
    ClientResponse createClient(ClientRequest request);

    @WebMethod
    ClientResponse getClientById(ClientRequest request);

    @WebMethod
    ClientResponse updateClient(ClientRequest request);

    @WebMethod
    ClientResponse deleteClient(ClientRequest request);
}
