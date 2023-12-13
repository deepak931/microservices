package com.bookmymovie.api.bookingapp.entity;

import com.bookmymovie.api.bookingapp.constants.SeatType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Seat extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private Long seatId;

    private String seatNo;

    @Enumerated(EnumType.STRING)
    private SeatType type;


}
