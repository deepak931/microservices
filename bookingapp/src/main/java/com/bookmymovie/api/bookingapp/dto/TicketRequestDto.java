package com.bookmymovie.api.bookingapp.dto;

import com.bookmymovie.api.bookingapp.constants.BookingStatus;
import lombok.Data;

@Data
public class TicketRequestDto {
    Long bookingId;
    Long seatReservationId;
    BookingStatus status;
}
