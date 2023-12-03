package com.bookmymovie.api.bookingapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class BookingRequestDto {


    @NotEmpty
    private Long movieID;

    @NotEmpty
    private Long theatreId;

    @NotEmpty
    private Long showId;

    @NotEmpty
    private Long userId;

    @NotEmpty
    private List<Long> seatsId;

    @NotEmpty
    private LocalDate bookingDate;

}
