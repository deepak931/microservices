package com.bookmymovie.api.bookingapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Booking extends BaseEntity {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native")
  private Long bookingId;

  private LocalDateTime bookingDate;

  @OneToOne(cascade = CascadeType.ALL)
  private Ticket ticket;
}
