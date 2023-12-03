package com.bookmymovie.api.bookingapp.service.impl;

import com.bookmymovie.api.bookingapp.constants.BookingStatus;
import com.bookmymovie.api.bookingapp.dto.BookingRequestDto;
import com.bookmymovie.api.bookingapp.dto.MovieBookingDto;
import com.bookmymovie.api.bookingapp.entity.*;
import com.bookmymovie.api.bookingapp.repository.*;
import com.bookmymovie.api.bookingapp.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    private SeatReservationRepository seatReservationRepository;

    private TheatreRepository theatreRepository;

    private UserRepository userRepository;

    private SeatRepository seatRepository;

    private TicketRepository ticketRepository;

    private StreamBridge streamBridge;


    @Override

    public SeatReservation createBooking(BookingRequestDto bookingDto) {

        Theatre theatre = theatreRepository.findById(bookingDto.getTheatreId()).get();

        Movie movie = theatre.getMovie();

        User user = userRepository.findById(bookingDto.getUserId()).get();

        Shows shows =
                movie.getShows().stream().filter(e -> e.getShowId() == bookingDto.getShowId()).findFirst()
                        .get();
        Booking booking = new Booking();
        booking.setBookingDate(bookingDto.getBookingDate());
        booking.setUser(user);
        booking.setStatus(BookingStatus.PENDING);

        booking = bookingRepository.save(booking);

        SeatReservation seatReservation = new SeatReservation();
        seatReservation.setBooking(booking);
        seatReservation.setUser(user);
        String seatIds =
                bookingDto.getSeatsId().stream().map(Object::toString).collect(Collectors.joining(","));
        seatReservation.setSeatIds(seatIds);
        seatReservation.setDate(bookingDto.getBookingDate());
        seatReservation.setMovie(movie);
        seatReservation.setTheatre(theatre);
        seatReservation.setShows(shows);

        seatReservationRepository.save(seatReservation);
        return seatReservation;

    }

    @Override
    public void updateBooking(Long bookingId, Long seatReservationId, BookingStatus status) {
        Booking booking = bookingRepository.findById(bookingId).get();
        SeatReservation seatReservation = seatReservationRepository.findById(seatReservationId).get();

        List<Long> seatIds =
                Arrays.stream(seatReservation.getSeatIds().split(",")).map(e -> Long.valueOf(e)).toList();

        if (status == BookingStatus.SUCCESS) {
            Ticket ticket = new Ticket();
            List<Seat> seatBySeatIdn = seatRepository.findSeatBySeatIdIn(seatIds);
            Set<Seat> seats = new HashSet<>(seatBySeatIdn);
            ticket.setShows(seatReservation.getShows());
            ticket.setSeats(seats);
            ticket.setShows(seatReservation.getShows());
            ticket.setShowDate(seatReservation.getDate());

            booking.setStatus(BookingStatus.SUCCESS);

            bookingRepository.save(booking);
            ticketRepository.save(ticket);
            seatReservationRepository.delete(seatReservation);

        } else {
            booking.setStatus(BookingStatus.FAILED);

            bookingRepository.save(booking);
            seatReservationRepository.delete(seatReservation);
        }

    }


    @Override
    public List<BookingRequestDto> getAllBookings() {
        return null;
    }

    @Override
    public void notifyPayment(SeatReservation seatReservation) {
        MovieBookingDto movieBookingDto = new MovieBookingDto();
        movieBookingDto.setBookingId(seatReservation.getBooking().getBookingId());
        movieBookingDto.setStatus(BookingStatus.PENDING.name());
        movieBookingDto.setAmount(200d);
        movieBookingDto.setSeatReservationId(seatReservation.getReservationId());
        streamBridge.send("sendPaymentReq-out-0", movieBookingDto);

    }
}
