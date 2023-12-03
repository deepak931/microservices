package com.bookmymovie.api.bookingapp.service;

import com.bookmymovie.api.bookingapp.constants.BookingStatus;
import com.bookmymovie.api.bookingapp.dto.BookingRequestDto;
import com.bookmymovie.api.bookingapp.entity.SeatReservation;

import java.util.List;

public interface BookingService {

    /**
     * @param bookingDto
     */
    SeatReservation createBooking(BookingRequestDto bookingDto);

    void updateBooking(Long bookingId, Long seatReservationId, BookingStatus status);

    List<BookingRequestDto> getAllBookings();

    void notifyPayment(SeatReservation seatReservation);

}
