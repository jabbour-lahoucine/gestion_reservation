package com.example.gestion_reservation.RestController;

import com.example.gestion_reservation.entities.Client;
import com.example.gestion_reservation.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Client")
public class clientcontroller {
    @Autowired
    private ClientRepository clientRepository;

    //READ:Récuperer tous les clients
    @GetMapping("/clients")
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    //READ:Récuperer un client par son id
    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        return clientRepository.findById(id).map(client -> ResponseEntity.ok().body(client))
                .orElse(ResponseEntity.notFound().build());
    }

    //CREATE:Ajouter un nouveau client
    @PostMapping("/client")
    public Client createClient (@RequestBody Client client){
        return clientRepository.save(client);
    }

    //UPDATE:Mettre à jour un client existant
    @PutMapping("/client/{id}")
    public ResponseEntity<Client> updateclient(@PathVariable Long id,@RequestBody Client clientDetails){
        return clientRepository.findById(id).map(client ->{
            client.setNom(clientDetails.getNom());
            client.setPrenom(clientDetails.getPrenom());
            client.setEmail(clientDetails.getEmail());
            client.setTelephone(clientDetails.getTelephone());
            Client updateClient = clientRepository.save(client);
            return ResponseEntity.ok().body(updateClient);
        }).orElse(ResponseEntity.notFound().build());
    }

    //DELETE:Supprimer un client
    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        return clientRepository.findById(id).map(client -> {
            clientRepository.delete(client);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
