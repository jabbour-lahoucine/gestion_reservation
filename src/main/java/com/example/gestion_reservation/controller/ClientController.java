package com.example.gestion_reservation.controller;

import com.example.gestion_reservation.entities.Client;
import com.example.gestion_reservation.entities.Reservation;
import com.example.gestion_reservation.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    // Query to fetch all clients
    @QueryMapping
    public List<Client> clients() {
        return clientRepository.findAll();
    }

    // Query to fetch a client by ID
    @QueryMapping
    public Client client(@Argument Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    // Fetch reservations for a specific client
    @QueryMapping
    public List<Reservation> reservationsForClient(@Argument Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        return client.getReservations();
    }

    // Mutation to create a new client
    @MutationMapping
    public Client createClient(
            @Argument String nom,
            @Argument String prenom,
            @Argument String email,
            @Argument String telephone
    ) {
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setEmail(email);
        client.setTelephone(telephone);
        return clientRepository.save(client);
    }

    // Mutation to update an existing client
    @MutationMapping
    public Client updateClient(
            @Argument Long id,
            @Argument String nom,
            @Argument String prenom,
            @Argument String email,
            @Argument String telephone
    ) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        if (nom != null) client.setNom(nom);
        if (prenom != null) client.setPrenom(prenom);
        if (email != null) client.setEmail(email);
        if (telephone != null) client.setTelephone(telephone);
        return clientRepository.save(client);
    }

    // Mutation to delete a client
    @MutationMapping
    public Boolean deleteClient(@Argument Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
