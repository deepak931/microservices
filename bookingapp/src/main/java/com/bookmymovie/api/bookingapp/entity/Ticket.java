package com.bookmymovie.api.bookingapp.entity;

import com.bookmymovie.api.bookingapp.constants.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ticket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private Long ticketId;

    private LocalDate showDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Shows shows;

    @ManyToOne
    private Movie movie;

    @OneToMany
    private Set<Seat> seats;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
