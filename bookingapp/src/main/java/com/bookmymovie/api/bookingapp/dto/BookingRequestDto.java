package com.bookmymovie.api.bookingapp.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class BookingRequestDto {



  private Long movieID;

  private Long theatreId;

  private Long showId;

  private Long userId;

  private List<Long> seatsId;

  private LocalDate bookingDate;

}
