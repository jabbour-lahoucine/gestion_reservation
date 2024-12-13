package com.example.gestion_reservation.config;

import com.example.gestion_reservation.SOAP.Ws.ChambreSoapService;
import com.example.gestion_reservation.SOAP.Ws.ClientSoapService;
import com.example.gestion_reservation.SOAP.Ws.ReservationSoapService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.bus.spring.SpringBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class    CxConfig {

    private final Bus bus;
    @Lazy
    private final ClientSoapService clientSoapService;
    @Lazy
    private final ChambreSoapService chambreSoapService;
    @Lazy
    private final ReservationSoapService reservationSoapService;

    // Injection de dépendances avec @Lazy pour éviter la dépendance circulaire
    public CxConfig(Bus bus, @Lazy ClientSoapService clientSoapService, @Lazy ChambreSoapService chambreSoapService,
                    @Lazy ReservationSoapService reservationSoapService) {
        this.bus = bus;
        this.clientSoapService = clientSoapService;
        this.chambreSoapService = chambreSoapService;
        this.reservationSoapService = reservationSoapService;
    }

    // Le bus CXF est défini une seule fois ici.
    @Bean
    public Bus cxfBus() {
        return new SpringBus();
    }

    @Bean
    public EndpointImpl clientEndpoint() {
        // Créer l'endpoint pour le service Client
        EndpointImpl endpoint = new EndpointImpl(bus, clientSoapService);
        endpoint.publish("/client");
        return endpoint;
    }

    @Bean
    public EndpointImpl chambreEndpoint() {
        // Créer l'endpoint pour le service Chambre
        EndpointImpl endpoint = new EndpointImpl(bus, chambreSoapService);
        endpoint.publish("/chambre");
        return endpoint;
    }

    @Bean
    public EndpointImpl reservationEndpoint() {
        // Créer l'endpoint pour le service Reservation
        EndpointImpl endpoint = new EndpointImpl(bus, reservationSoapService);
        endpoint.publish("/reservation");
        return endpoint;
    }
}
