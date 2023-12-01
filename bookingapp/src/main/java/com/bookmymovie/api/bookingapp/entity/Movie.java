package com.bookmymovie.api.bookingapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native")
  private Long movieId;

  private String title;

  private int year;

  private LocalDateTime releaseDate;

  private String director;

  private String genre;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Shows> shows;

  @OneToMany(fetch = FetchType.LAZY)
  private Set<Theatre> theatres;


}
