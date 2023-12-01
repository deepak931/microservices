package com.bookmymovie.api.bookingapp.service;

import com.bookmymovie.api.bookingapp.dto.BookingDto;

import java.util.List;

public interface BookingService {

  /**
   * @param bookingDto
   */
  void creatBooking(BookingDto bookingDto);

  List<BookingDto> getAllBookings();

}
