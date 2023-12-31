package com.ars.airlinereservationsystem.model;

import com.ars.airlinereservationsystem.model.util.FlightStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "fight")
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "flight_status")
    private FlightStatus flightStatus;
    @Column(name = "code")
    private String flightCode;
    @OneToMany(mappedBy = "flight")
    private List<Schedule> scheduleList;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
    @OneToMany(mappedBy ="flight")
    private List<Booking> bookingList;
}
