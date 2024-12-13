package com.example.gestion_reservation.grpcServer;
import com.example.gestion_reservation.*;
import com.example.gestion_reservation.services.ClientService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@GrpcService
@Service
public class ClientServiceImpl extends ClientServiceGrpc.ClientServiceImplBase {

    @Autowired
    private ClientService clientService;

    @Override
    public void createClient(CreateClientRequest request, StreamObserver<Client> responseObserver) {
        try {
            com.example.gestion_reservation.entities.Client clientEntity = new com.example.gestion_reservation.entities.Client(
                    request.getNom(),
                    request.getPrenom(),
                    request.getEmail(),
                    request.getTelephone()
            );
            com.example.gestion_reservation.entities.Client createdClient = clientService.createClient(clientEntity);

            com.example.gestion_reservation.Client response = com.example.gestion_reservation.Client.newBuilder()
                    .setId(createdClient.getId())
                    .setNom(createdClient.getNom())
                    .setPrenom(createdClient.getPrenom())
                    .setEmail(createdClient.getEmail())
                    .setTelephone(createdClient.getTelephone())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void updateClient(UpdateClientRequest request, StreamObserver<Client> responseObserver) {
        try {
            Optional<com.example.gestion_reservation.entities.Client> clientOptional = clientService.getClientById(request.getId());
            if (clientOptional.isPresent()) {
                com.example.gestion_reservation.entities.Client clientEntity = clientOptional.get();
                clientEntity.setNom(request.getNom());
                clientEntity.setPrenom(request.getPrenom());
                clientEntity.setEmail(request.getEmail());
                clientEntity.setTelephone(request.getTelephone());

                com.example.gestion_reservation.entities.Client updatedClient = clientService.createClient(clientEntity);

                com.example.gestion_reservation.Client response = com.example.gestion_reservation.Client.newBuilder()
                        .setId(updatedClient.getId())
                        .setNom(updatedClient.getNom())
                        .setPrenom(updatedClient.getPrenom())
                        .setEmail(updatedClient.getEmail())
                        .setTelephone(updatedClient.getTelephone())
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(new RuntimeException("Client not found with id " + request.getId()));
            }
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void deleteClient(DeleteClientRequest request, StreamObserver<com.google.protobuf.Empty> responseObserver) {
        try {
            clientService.deleteClient(request.getId());
            responseObserver.onNext(com.google.protobuf.Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getClient(GetClientRequest request, StreamObserver<com.example.gestion_reservation.Client> responseObserver) {
        try {
            Optional<com.example.gestion_reservation.entities.Client> clientOptional = clientService.getClientById(request.getId());
            if (clientOptional.isPresent()) {
                com.example.gestion_reservation.entities.Client clientEntity = clientOptional.get();

                com.example.gestion_reservation.Client response = com.example.gestion_reservation.Client.newBuilder()
                        .setId(clientEntity.getId())
                        .setNom(clientEntity.getNom())
                        .setPrenom(clientEntity.getPrenom())
                        .setEmail(clientEntity.getEmail())
                        .setTelephone(clientEntity.getTelephone())
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(new RuntimeException("Client not found with id " + request.getId()));
            }
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}
