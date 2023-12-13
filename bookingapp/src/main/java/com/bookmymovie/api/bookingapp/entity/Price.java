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
public class Price extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private Long priceId;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    

}
