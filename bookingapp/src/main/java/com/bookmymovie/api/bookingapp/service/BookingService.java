package com.bookmymovie.api.bookingapp.service;

import com.bookmymovie.api.bookingapp.constants.BookingStatus;
import com.bookmymovie.api.bookingapp.dto.BookingRequestDto;
import com.bookmymovie.api.bookingapp.dto.MovieBookingDto;
import com.bookmymovie.api.bookingapp.entity.SeatReservation;

import java.util.List;

public interface BookingService {

    /**
     * @param bookingDto
     */
    SeatReservation createBooking(BookingRequestDto bookingDto);

    Long updateBooking(Long bookingId, Long seatReservationId, BookingStatus status);

    List<BookingRequestDto> getAllBookings();

    public MovieBookingDto getBooking(Long id);

    void notifyPayment(SeatReservation seatReservation);

}
