package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.dto.BookingRequestDto;
import com.bookmymovie.api.bookingapp.entity.Booking;

public class BookingMapper {

  public static Booking mapToBooking(BookingRequestDto bookingDto, Booking booking) {
    booking.setBookingDate(bookingDto.getBookingDate());

    return booking;
  }

  public static BookingRequestDto mapToBookingDto(Booking booking, BookingRequestDto bookingDto) {

    return bookingDto;
  }
}
