package com.example.gestion_reservation.config;

import com.example.gestion_reservation.SOAP.Ws.ChambreSoapService;
import com.example.gestion_reservation.SOAP.Ws.ClientSoapService;
import com.example.gestion_reservation.SOAP.Ws.ReservationSoapService;
import lombok.NoArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@NoArgsConstructor
public class CxConfig {


        private Bus bus;
        private ClientSoapService clientSoapService;
        private ChambreSoapService chambreSoapService;
        private ReservationSoapService reservationSoapService;


        public CxConfig(Bus bus, ClientSoapService clientSoapService, ChambreSoapService chambreSoapService,
                        ReservationSoapService reservationSoapService) {
            this.bus = bus;
            this.clientSoapService = clientSoapService;
            this.chambreSoapService = chambreSoapService;
            this.reservationSoapService = reservationSoapService;

        }
        @Bean
        public Bus cxfBus() {
            return new SpringBus();
        }
        @Bean
        public EndpointImpl clientEndpoint() {
            EndpointImpl endpoint = new EndpointImpl(bus, clientSoapService);
            endpoint.publish("/client"); // L'URL sera /ws/client
            return endpoint;
        }

        @Bean
        public EndpointImpl chambreEndpoint() {
            EndpointImpl endpoint = new EndpointImpl(bus, chambreSoapService);
            endpoint.publish("/chambre"); // L'URL sera /ws/chambre
            return endpoint;
        }

        @Bean
        public EndpointImpl reservationEndpoint() {
            EndpointImpl endpoint = new EndpointImpl(bus, reservationSoapService);
            endpoint.publish("/reservation"); // L'URL sera /ws/reservation
            return endpoint;
        }


}
