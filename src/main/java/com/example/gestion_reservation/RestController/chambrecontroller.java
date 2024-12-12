package com.example.gestion_reservation.RestController;

import com.example.gestion_reservation.entities.Chambre;
import com.example.gestion_reservation.repositories.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chambre")
public class chambrecontroller {

    @Autowired
    private ChambreRepository chambreRepository;

    //READ:Récuperer toutes les chambres
    @GetMapping("/chambres")
    public List<Chambre> getAllChambres(){
        return chambreRepository.findAll();
    }

    //READ:Récuperer une chambre par son id
    @GetMapping("/chambre/{id}")
    public ResponseEntity<Chambre> getChambreById(@PathVariable Long id){
        return chambreRepository.findById(id).map(chambre -> ResponseEntity.ok().body(chambre))
                .orElse(ResponseEntity.notFound().build());
    }

    //CREATE:Ajouter une nouvelle chambre
    @PostMapping("/chambre")
    public Chambre createChambre (@RequestBody Chambre chambre){
        return chambreRepository.save(chambre);
    }

    //UPDATE:Mettre à jour une chambre existante
    @PutMapping("/chambre/{id}")
    public ResponseEntity<Chambre> updateChambre(@PathVariable Long id,@RequestBody Chambre chambreDetails){
        return chambreRepository.findById(id).map(chambre ->{
            chambre.setType(chambreDetails.getType());
            chambre.setPrix(chambreDetails.getPrix());
            chambre.setDisponible(chambreDetails.getDisponible());
            Chambre updateChambre = chambreRepository.save(chambre);
            return ResponseEntity.ok().body(updateChambre);
        }).orElse(ResponseEntity.notFound().build());
    }

    //DELETE:Supprimer une chambre
    @DeleteMapping("/chambre/{id}")
    public ResponseEntity<Void> deleteChambre(@PathVariable Long id){
        return chambreRepository.findById(id).map(chambre -> {
            chambreRepository.delete(chambre);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}

