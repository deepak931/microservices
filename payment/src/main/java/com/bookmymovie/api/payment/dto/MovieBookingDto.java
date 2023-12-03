package com.bookmymovie.api.payment.dto;

import lombok.Data;

@Data
public class MovieBookingDto {


  Long bookingId;
  Long seatReservationId;
  String status;

  Double amount;
}
