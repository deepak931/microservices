package com.bookmymovie.api.bookingapp.controller;


import com.bookmymovie.api.bookingapp.dto.BookingRequestDto;
import com.bookmymovie.api.bookingapp.dto.MovieBookingDto;
import com.bookmymovie.api.bookingapp.dto.ResponseDto;
import com.bookmymovie.api.bookingapp.dto.TicketRequestDto;
import com.bookmymovie.api.bookingapp.entity.SeatReservation;
import com.bookmymovie.api.bookingapp.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bookmymovie.api.bookingapp.constants.BookingAppConstants.messageCreated;

@RestController
@RequestMapping(path = "/bookmymovie/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BookingControllerApi {


    private BookingService bookingService;


    @PostMapping("/booking")
    public ResponseEntity<ResponseDto> bookMovieTickets(@RequestBody BookingRequestDto booking) {

        SeatReservation seatReservation = bookingService.createBooking(booking);
        bookingService.notifyPayment(seatReservation);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.name(), messageCreated("Booking",
                        seatReservation.getBooking().getBookingId().toString())));

    }

    @PostMapping("/booking/ticket")
    public ResponseEntity<ResponseDto> createTicket(@RequestBody TicketRequestDto ticketRequestDto) {

        Long id = bookingService.updateBooking(ticketRequestDto.getBookingId(),
                ticketRequestDto.getSeatReservationId(), ticketRequestDto.getStatus());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.name(), messageCreated("Ticket", id.toString())));


    }

    @GetMapping("/booking/status/{id}")
    public ResponseEntity<MovieBookingDto> bookingStatus(@PathVariable Long id) {

        MovieBookingDto booking = bookingService.getBooking(id);

        return ResponseEntity.status(HttpStatus.OK).body(booking);


    }

}
