package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.dto.BookingDto;
import com.bookmymovie.api.bookingapp.entity.Booking;

public class BookingMapper {

  public static Booking mapToBooking(BookingDto bookingDto, Booking booking) {
    booking.setBookingDate(bookingDto.getBookingDate());

    return booking;
  }

  public static BookingDto mapToBookingDto(Booking booking, BookingDto bookingDto) {

    return bookingDto;
  }
}
