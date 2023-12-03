package com.bookmymovie.api.bookingapp.dto;

import lombok.Data;

@Data
public class MovieBookingDto {


  Long bookingId;
  Long seatReservationId;
  String status;

  Double amount;
}
