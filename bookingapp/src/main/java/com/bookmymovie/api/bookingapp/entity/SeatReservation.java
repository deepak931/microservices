package com.bookmymovie.api.bookingapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SeatReservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private Long reservationId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Shows shows;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Theatre theatre;

    @OneToOne
    private Booking booking;

    private LocalDate date;

    private String seatIds;


}
