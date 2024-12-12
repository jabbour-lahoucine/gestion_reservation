package com.example.gestion_reservation.grpcServer;


import com.example.gestion_reservation.ReservationServiceGrpc;
import com.example.gestion_reservation.*;
import com.example.gestion_reservation.entities.Client;
import com.example.gestion_reservation.services.ChambreService;
import com.example.gestion_reservation.services.ClientService;
import com.example.gestion_reservation.services.ReservationService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Optional;

@GrpcService
public class ReservationServiceImpl extends ReservationServiceGrpc.ReservationServiceImplBase {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ChambreService chambreService;

    @Override
    public void createReservation(CreateReservationRequest request, StreamObserver<Reservation> responseObserver) {
        try {
            Optional<Client> clientOptional = clientService.getClientById(request.getClientId());
            Optional<com.example.gestion_reservation.entities.Chambre> chambreOptional = chambreService.getChambreById(request.getChambreId());

            if (clientOptional.isPresent() && chambreOptional.isPresent()) {
                com.example.gestion_reservation.entities.Reservation reservationEntity = new com.example.gestion_reservation.entities.Reservation(
                        clientOptional.get(),
                        chambreOptional.get(),
                        new SimpleDateFormat("yyyy-MM-dd").parse(request.getDateDebut()),
                        new SimpleDateFormat("yyyy-MM-dd").parse(request.getDateFin()),
                        request.getPreferences()
                );

                com.example.gestion_reservation.entities.Reservation createdReservation = reservationService.createReservation(reservationEntity);

                Reservation response = Reservation.newBuilder()
                        .setId(createdReservation.getId())
                        .setClientId(createdReservation.getClient().getId())
                        .setChambreId(createdReservation.getChambre().getId())
                        .setDateDebut(new SimpleDateFormat("yyyy-MM-dd").format(createdReservation.getDateDebut()))
                        .setDateFin(new SimpleDateFormat("yyyy-MM-dd").format(createdReservation.getDateFin()))
                        .setPreferences(createdReservation.getPreferences())
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(new RuntimeException("Client or Chambre not found"));
            }
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void updateReservation(UpdateReservationRequest request, StreamObserver<Reservation> responseObserver) {
        try {
            Optional<com.example.gestion_reservation.entities.Client> clientOptional = clientService.getClientById(request.getClientId());
            Optional<com.example.gestion_reservation.entities.Chambre> chambreOptional = chambreService.getChambreById(request.getChambreId());

            if (clientOptional.isPresent() && chambreOptional.isPresent()) {
                com.example.gestion_reservation.entities.Reservation reservationEntity = new com.example.gestion_reservation.entities.Reservation(
                        clientOptional.get(),
                        chambreOptional.get(),
                        new SimpleDateFormat("yyyy-MM-dd").parse(request.getDateDebut()),
                        new SimpleDateFormat("yyyy-MM-dd").parse(request.getDateFin()),
                        request.getPreferences()
                );

                com.example.gestion_reservation.entities.Reservation updatedReservation = reservationService.updateReservation(request.getId(), reservationEntity);

                Reservation response = Reservation.newBuilder()
                        .setId(updatedReservation.getId())
                        .setClientId(updatedReservation.getClient().getId())
                        .setChambreId(updatedReservation.getChambre().getId())
                        .setDateDebut(new SimpleDateFormat("yyyy-MM-dd").format(updatedReservation.getDateDebut()))
                        .setDateFin(new SimpleDateFormat("yyyy-MM-dd").format(updatedReservation.getDateFin()))
                        .setPreferences(updatedReservation.getPreferences())
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(new RuntimeException("Client or Chambre not found"));
            }
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void deleteReservation(DeleteReservationRequest request, StreamObserver<com.google.protobuf.Empty> responseObserver) {
        try {
            reservationService.deleteReservation(request.getId());
            responseObserver.onNext(com.google.protobuf.Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getReservation(GetReservationRequest request, StreamObserver<Reservation> responseObserver) {
        try {
            Optional<com.example.gestion_reservation.entities.Reservation> reservationOptional = Optional.ofNullable(reservationService.findReservationById(request.getId()));
            if (reservationOptional.isPresent()) {
                com.example.gestion_reservation.entities.Reservation reservationEntity = reservationOptional.get();

                Reservation response = Reservation.newBuilder()
                        .setId(reservationEntity.getId())
                        .setClientId(reservationEntity.getClient().getId())
                        .setChambreId(reservationEntity.getChambre().getId())
                        .setDateDebut(new SimpleDateFormat("yyyy-MM-dd").format(reservationEntity.getDateDebut()))
                        .setDateFin(new SimpleDateFormat("yyyy-MM-dd").format(reservationEntity.getDateFin()))
                        .setPreferences(reservationEntity.getPreferences())
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(new RuntimeException("Reservation not found with id " + request.getId()));
            }
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}

