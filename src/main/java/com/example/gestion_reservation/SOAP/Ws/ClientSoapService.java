package com.example.gestion_reservation.SOAP.Ws;

import com.example.gestion_reservation.CreateClientRequest;
import com.example.gestion_reservation.entities.Client;
import com.example.gestion_reservation.repositories.ClientRepository;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@WebService(serviceName = "ClientSoapService")
public class ClientSoapService {

    @Autowired
    public ClientSoapService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    private ClientRepository clientRepository;

    public Client createClient(CreateClientRequest request) {
        Client client = new Client();
        client.setNom(request.getNom());
        client.setPrenom(request.getPrenom());
        client.setEmail(request.getEmail());
        client.setTelephone(request.getTelephone());
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public Client updateClient(Long id, CreateClientRequest request) {
        return clientRepository.findById(id).map(client -> {
            client.setNom(request.getNom());
            client.setPrenom(request.getPrenom());
            client.setEmail(request.getEmail());
            client.setTelephone(request.getTelephone());
            return clientRepository.save(client);
        }).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
