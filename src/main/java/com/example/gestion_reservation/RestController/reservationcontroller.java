package com.example.gestion_reservation.RestController;

import com.example.gestion_reservation.entities.Reservation;
import com.example.gestion_reservation.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Reservation")
public class reservationcontroller {
    @Autowired
    private ReservationRepository reservationRepository;

    //READ:Récuperer toutes les reservations
    @GetMapping("/reservations")
    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    //READ:Récuperer une reservation par son id
    @GetMapping("/reservation/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id){
        return reservationRepository.findById(id).map(reservation -> ResponseEntity.ok().body(reservation))
                .orElse(ResponseEntity.notFound().build());
    }

    //CREATE:Ajouter une nouvelle reservation
    @PostMapping("/reservation")
    public Reservation createReservation (@RequestBody Reservation reservation){
        return reservationRepository.save(reservation);
    }

    //UPDATE:Mettre à jour un reservation existante
    @PutMapping("/reservation/{id}")
    public ResponseEntity<Reservation> updatereservation(@PathVariable Long id,@RequestBody Reservation reservationDetails){
        return reservationRepository.findById(id).map(reservation ->{
            reservation.setClient(reservationDetails.getClient());
            reservation.setChambre(reservationDetails.getChambre());
            reservation.setDateDebut(reservationDetails.getDateDebut());
            reservation.setDateFin(reservationDetails.getDateFin());
            reservation.setPreferences(reservationDetails.getPreferences());
            Reservation updateReservation = reservationRepository.save(reservation);
            return ResponseEntity.ok().body(updateReservation);
        }).orElse(ResponseEntity.notFound().build());
    }

    //DELETE:Supprimer une reservation
    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id){
        return reservationRepository.findById(id).map(reservation -> {
            reservationRepository.delete(reservation);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}

