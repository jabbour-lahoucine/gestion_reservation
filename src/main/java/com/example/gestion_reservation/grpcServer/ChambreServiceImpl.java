//package com.example.gestion_reservation.grpcServer;
//
//
//import com.example.gestion_reservation.entities.Chambre;
//import com.example.gestion_reservation.services.ChambreService;
//import net.devh.boot.grpc.server.service.GrpcService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Optional;
//
//@GrpcService
//public class ChambreServiceImpl extends ChambreServiceGrpc.ChambreServiceImplBase {
//
//    @Autowired
//    private ChambreService chambreService;
//
//    @Override
//    public void createChambre(CreateChambreRequest request, StreamObserver<Chambre> responseObserver) {
//        try {
//            com.example.gestion_reservation.entities.Chambre chambreEntity = new com.example.gestion_reservation.entities.Chambre(
//                    request.getType(),
//                    request.getPrix(),
//                    request.getDisponible()
//            );
//
//            com.example.gestion_reservation.entities.Chambre createdChambre = chambreService.createChambre(chambreEntity);
//
//            com.example.gestion_reservation.Chambre response = com.example.gestion_reservation.Chambre.newBuilder()
//                    .setId(createdChambre.getId())
//                    .setType(createdChambre.getType())
//                    .setPrix(createdChambre.getPrix())
//                    .setDisponible(createdChambre.getDisponible())
//                    .build();
//
//            responseObserver.onNext(response);
//            responseObserver.onCompleted();
//        } catch (Exception e) {
//            responseObserver.onError(e);
//        }
//    }
//
//    @Override
//    public void updateChambre(UpdateChambreRequest request, StreamObserver<com.example.gestion_reservation_grpc.Chambre> responseObserver) {
//        try {
//            Optional<com.example.gestion_reservation_grpc.entities.Chambre> chambreOptional = chambreService.getChambreById(request.getId());
//            if (chambreOptional.isPresent()) {
//                com.example.gestion_reservation_grpc.entities.Chambre chambreEntity = chambreOptional.get();
//                chambreEntity.setType(request.getType());
//                chambreEntity.setPrix(request.getPrix());
//                chambreEntity.setDisponible(request.getDisponible());
//
//                com.example.gestion_reservation_grpc.entities.Chambre updatedChambre = chambreService.createChambre(chambreEntity);
//
//                com.example.gestion_reservation_grpc.Chambre response = com.example.gestion_reservation_grpc.Chambre.newBuilder()
//                        .setId(updatedChambre.getId())
//                        .setType(updatedChambre.getType())
//                        .setPrix(updatedChambre.getPrix())
//                        .setDisponible(updatedChambre.getDisponible())
//                        .build();
//
//                responseObserver.onNext(response);
//                responseObserver.onCompleted();
//            } else {
//                responseObserver.onError(new RuntimeException("Chambre not found with id " + request.getId()));
//            }
//        } catch (Exception e) {
//            responseObserver.onError(e);
//        }
//    }
//
//    @Override
//    public void deleteChambre(DeleteChambreRequest request, StreamObserver<com.google.protobuf.Empty> responseObserver) {
//        try {
//            chambreService.deleteChambre(request.getId());
//            responseObserver.onNext(com.google.protobuf.Empty.newBuilder().build());
//            responseObserver.onCompleted();
//        } catch (Exception e) {
//            responseObserver.onError(e);
//        }
//    }
//
//    @Override
//    public void getChambre(GetChambreRequest request, StreamObserver<com.example.gestion_reservation_grpc.Chambre> responseObserver) {
//        try {
//            Optional<com.example.gestion_reservation_grpc.entities.Chambre> chambreOptional = chambreService.getChambreById(request.getId());
//            if (chambreOptional.isPresent()) {
//                com.example.gestion_reservation_grpc.entities.Chambre chambreEntity = chambreOptional.get();
//
//                com.example.gestion_reservation_grpc.Chambre response = com.example.gestion_reservation_grpc.Chambre.newBuilder()
//                        .setId(chambreEntity.getId())
//                        .setType(chambreEntity.getType())
//                        .setPrix(chambreEntity.getPrix())
//                        .setDisponible(chambreEntity.getDisponible())
//                        .build();
//
//                responseObserver.onNext(response);
//                responseObserver.onCompleted();
//            } else {
//                responseObserver.onError(new RuntimeException("Chambre not found with id " + request.getId()));
//            }
//        } catch (Exception e) {
//            responseObserver.onError(e);
//        }
//    }
//}
//
