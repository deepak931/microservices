package com.bookmymovie.api.bookingapp.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;


@Data
public class BookingDto {



  private Long bookingId;

  private LocalDateTime bookingDate;

  private Set<TicketDto> tickets;
}
