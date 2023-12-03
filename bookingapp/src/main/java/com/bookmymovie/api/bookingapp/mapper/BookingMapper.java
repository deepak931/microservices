package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.dto.BookingRequestDto;
import com.bookmymovie.api.bookingapp.dto.MovieBookingDto;
import com.bookmymovie.api.bookingapp.entity.Booking;

public class BookingMapper {

    public static Booking mapToBooking(BookingRequestDto bookingDto, Booking booking) {
        booking.setBookingDate(bookingDto.getBookingDate());

        return booking;
    }

    public static MovieBookingDto mapToBookingDto(Booking booking, MovieBookingDto bookingDto) {
        bookingDto.setBookingId(booking.getBookingId());
        bookingDto.setStatus(booking.getStatus().name());
        return bookingDto;
    }
}
