package com.example.gestion_reservation.SOAP.entities;

import com.example.gestion_reservation.entities.Chambre;
import com.example.gestion_reservation.entities.Client;
import jakarta.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement
public class ReservationRequest {
    private Long reservationId;
    private Client client;
    private Chambre chambre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String preferences;  // Changement ici pour que preferences soit un String

    @XmlElement
    @NotNull(message = "Le client est obligatoire")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @XmlElement
    @NotNull(message = "La chambre est obligatoire")
    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    @XmlElement
    @NotNull(message = "La date de début est obligatoire")
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    @XmlElement
    @NotNull(message = "La date de fin est obligatoire")
    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    @XmlElement
    public String getPreferences() {  // Utiliser un String pour preferences
        return preferences;
    }

    public void setPreferences(String preferences) {  // Assurer que preferences est un String
        this.preferences = preferences;
    }

    // Getter and Setter for reservationId
    @XmlElement
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
}
