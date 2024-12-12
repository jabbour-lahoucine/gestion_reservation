package com.example.gestion_reservation.SOAP.entities;

import com.example.gestion_reservation.entities.Chambre;
import com.example.gestion_reservation.entities.Client;
import jakarta.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.Date;

@XmlRootElement
public class ReservationRequest {
    private Long reservationId;
    private Client client;
    private Chambre chambre;
    private Date dateDebut;
    private Date dateFin;
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
    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    @XmlElement
    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
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
