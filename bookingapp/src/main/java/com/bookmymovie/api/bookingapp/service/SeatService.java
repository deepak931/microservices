package com.bookmymovie.api.bookingapp.service;

import com.bookmymovie.api.bookingapp.dto.SeatDto;

import java.util.List;

public interface SeatService {

  /**
   * @param seatDto
   */
  void creatSeat(SeatDto seatDto);

  List<SeatDto> getAllSeats();

}
