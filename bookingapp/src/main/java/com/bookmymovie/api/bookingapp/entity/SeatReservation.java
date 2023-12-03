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
public class SeatReservation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native")
  private Long reservationId;

  @OneToOne
  private User user;

  @OneToOne
  private Shows shows;

  @OneToOne
  private Movie movie;

  @OneToOne
  private Theatre theatre;

  @OneToOne
  private Booking booking;

  private LocalDate date;

  private String seatIds;



}
