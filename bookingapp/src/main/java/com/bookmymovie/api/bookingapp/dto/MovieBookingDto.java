package com.bookmymovie.api.bookingapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieBookingDto {


    Long bookingId;
    Long seatReservationId;
    String status;

    Double amount;
}
