package com.bookmymovie.api.bookingapp.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class MovieShowDto {
  private Long showId;

  private LocalTime startTime;

  private LocalTime endTime;

  List<SeatDto> seatDtos;
}
