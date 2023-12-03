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
public class Partner extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private Long partnerID;

    private String name;

    private String email;

    private String mobileNumber;

    private String address;

    @ManyToOne
    private City city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partner")
    private Set<Theatre> theatres;

}
