package com.example.gestion_reservation.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Date;

public class ReservationRequest {
    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private String preferences;
    private Long clientId;
    private Long chambreId;
    public ReservationRequest(){}
    @ConstructorBinding
    public ReservationRequest(Date dateDebut, Date dateFin, String preferences, Long clientId, Long chambreId) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.preferences = preferences;
        this.clientId = clientId;
        this.chambreId = chambreId;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getChambreId() {
        return chambreId;
    }

    public void setChambreId(Long chambreId) {
        this.chambreId = chambreId;
    }
}
