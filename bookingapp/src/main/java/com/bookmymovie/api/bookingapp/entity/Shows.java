package com.bookmymovie.api.bookingapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Shows extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native")
  private Long showId;

  private LocalTime startTime;

  private LocalTime endTime;

  @OneToMany
  private Set<Ticket> tickets;

  @ManyToOne
  private Movie movie;

  @ManyToOne
  private Theatre theatre;
}
