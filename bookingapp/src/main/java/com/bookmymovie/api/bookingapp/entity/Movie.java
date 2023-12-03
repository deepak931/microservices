package com.bookmymovie.api.bookingapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
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

    private LocalDateTime releaseDate;

    private String director;

    private String genre;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Shows> shows;

    @OneToOne(fetch = FetchType.EAGER)
    private Theatre theatres;

    @ManyToOne
    private City city;

    private boolean isRunning;

    private LocalDate startDate;

    private LocalDate endDate;

}
