package com.bookmymovie.api.bookingapp.controller;


import com.bookmymovie.api.bookingapp.constants.BookingAppConstants;
import com.bookmymovie.api.bookingapp.dto.BookingRequestDto;
import com.bookmymovie.api.bookingapp.dto.ResponseDto;
import com.bookmymovie.api.bookingapp.dto.TicketRequestDto;
import com.bookmymovie.api.bookingapp.entity.SeatReservation;
import com.bookmymovie.api.bookingapp.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bookmymovie/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingControllerApi {


    private BookingService bookingService;


    @PostMapping("/booking")
    public ResponseEntity<ResponseDto> bookMovieTickets(@RequestBody BookingRequestDto booking) {

        SeatReservation seatReservation = bookingService.createBooking(booking);
        bookingService.notifyPayment(seatReservation);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.name(), BookingAppConstants.MESSAGE_201));

    }

    @PostMapping("/booking/ticket")
    public ResponseEntity<ResponseDto> createTicket(@RequestBody TicketRequestDto ticketRequestDto) {

        bookingService.updateBooking(ticketRequestDto.getBookingId(),
                ticketRequestDto.getSeatReservationId(), ticketRequestDto.getStatus());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.name(), BookingAppConstants.MESSAGE_201));


    }

}
