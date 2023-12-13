package com.bookmymovie.api.bookingapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Theatre extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private Long theatreId;

    private String theatreName;

    private String theatreEmail;

    private String contactNumber;

    private String address;

    @ManyToOne
    private City city;

    @ManyToOne
    private Partner partner;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Seat> seats;

    @OneToMany
    private Set<Movie> movies;

    @OneToMany
    private Set<Price> prices;


    private String zipCode;


}
