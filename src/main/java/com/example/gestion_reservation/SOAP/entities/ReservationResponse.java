package com.example.gestion_reservation.SOAP.entities;

import com.example.gestion_reservation.entities.Chambre;
import com.example.gestion_reservation.entities.Client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement
public class ReservationResponse {
    private Long reservationId;
    private Client client;
    private Chambre chambre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String preferences;  // Changement ici : passer de Map<String, String> à String

    @XmlElement
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    @XmlElement
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @XmlElement
    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    @XmlElement
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    @XmlElement
    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    @XmlElement
    public String getPreferences() {
        return preferences;  // Retourne un String
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;  // Définit un String
    }
}
