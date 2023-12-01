package com.bookmymovie.api.bookingapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketDto {

  private Long ticketId;

  private Long seatId;

  private double price;

  private LocalDateTime showDate;

  private ShowsDto show;
}
