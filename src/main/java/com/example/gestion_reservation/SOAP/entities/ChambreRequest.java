package com.example.gestion_reservation.SOAP.entities;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
public class ChambreRequest {
    private Long chambreId;
    private String type;
    private double prix;
    private boolean disponible;


    @XmlElement
    public Long getChambreId() {
        return chambreId;
    }

    public void setChambreId(Long chambreId) {
        this.chambreId = chambreId;
    }
    @XmlElement
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public @NotNull(message = "Le prix est obligé") @Positive(message = "Il faut que le prix soit positif") double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @XmlElement
    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

}
