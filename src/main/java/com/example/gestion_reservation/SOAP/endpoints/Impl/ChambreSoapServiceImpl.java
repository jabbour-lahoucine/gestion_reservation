package com.example.gestion_reservation.SOAP.endpoints.Impl;

import com.example.gestion_reservation.SOAP.endpoints.interfaces.ChambreSoapService;
import com.example.gestion_reservation.SOAP.entities.ChambreRequest;
import com.example.gestion_reservation.SOAP.entities.ChambreResponse;
import com.example.gestion_reservation.entities.Chambre;
import com.example.gestion_reservation.services.ChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChambreSoapServiceImpl implements ChambreSoapService {
    private final ChambreService chambreService;

    @Autowired
    public ChambreSoapServiceImpl(ChambreService chambreService) {
        this.chambreService = chambreService;
    }

    @Override
    public ChambreResponse createChambre(ChambreRequest request) {
        Chambre chambre = new Chambre();
        chambre.setType(request.getType());
        chambre.setPrix(request.getPrix());
        chambre.setDisponible(request.isDisponible());

        Chambre savedChambre = chambreService.createChambre(chambre);

        ChambreResponse response = new ChambreResponse();
        response.setChambreId(savedChambre.getId());
        return response;
    }

    @Override
    public ChambreResponse getChambreById(ChambreRequest request) {
        Chambre chambre = chambreService.getChambreById(request.getChambreId())
                .orElseThrow(() -> new RuntimeException("Chambre not found"));

        ChambreResponse response = new ChambreResponse();
        response.setChambreId(chambre.getId());
        response.setType(chambre.getType());
        response.setPrix(chambre.getPrix());
        response.setDisponible(chambre.getDisponible());
        return response;
    }

    @Override
    public ChambreResponse updateChambre(ChambreRequest request) {
        Chambre chambre = chambreService.getChambreById(request.getChambreId())
                .orElseThrow(() -> new RuntimeException("Chambre not found"));

        chambre.setType(request.getType());
        chambre.setPrix(request.getPrix());
        chambre.setDisponible(request.isDisponible());

        Chambre updatedChambre = chambreService.createChambre(chambre);

        ChambreResponse response = new ChambreResponse();
        response.setChambreId(updatedChambre.getId());
        return response;
    }

    @Override
    public ChambreResponse deleteChambre(ChambreRequest request) {
        Chambre chambre = chambreService.getChambreById(request.getChambreId())
                .orElseThrow(() -> new RuntimeException("Chambre not found"));

        chambreService.deleteChambre(chambre.getId());

        ChambreResponse response = new ChambreResponse();
        response.setChambreId(chambre.getId());
        return response;
    }
}
