package com.example.gestion_reservation.services;

import com.example.gestion_reservation.entities.Chambre;
import com.example.gestion_reservation.repositories.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChambreService {
    
    @Autowired
    private ChambreRepository chambreRepository;

    public Chambre createChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    public Optional<Chambre> getChambreById(Long id) {
        return chambreRepository.findById(id);
    }

    public void deleteChambre(Long id) {
        if (chambreRepository.existsById(id)) {
            chambreRepository.deleteById(id);
        } else {
            throw new RuntimeException("Chambre not found with id " + id);
        }
    }
    
}
