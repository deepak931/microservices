package com.bookmymovie.api.bookingapp.service.impl;

import com.bookmymovie.api.bookingapp.constants.BookingStatus;
import com.bookmymovie.api.bookingapp.dto.BookingRequestDto;
import com.bookmymovie.api.bookingapp.dto.MovieBookingDto;
import com.bookmymovie.api.bookingapp.entity.*;
import com.bookmymovie.api.bookingapp.exception.BookingException;
import com.bookmymovie.api.bookingapp.exception.ResourceNotFoundExcption;
import com.bookmymovie.api.bookingapp.mapper.BookingMapper;
import com.bookmymovie.api.bookingapp.repository.*;
import com.bookmymovie.api.bookingapp.service.BookingService;
import com.bookmymovie.api.bookingapp.service.SeatReservationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    private SeatReservationRepository seatReservationRepository;

    private UserRepository userRepository;

    private SeatRepository seatRepository;
    private MovieRepository movieRepository;

    private TicketRepository ticketRepository;

    private StreamBridge streamBridge;

    private SeatReservationService seatReservationService;


    @Override
    @Transactional
    public SeatReservation createBooking(BookingRequestDto bookingDto) {

        Movie movie = movieRepository.findById(bookingDto.getMovieID()).orElseThrow(() -> new ResourceNotFoundExcption(
                "Movie",
                "movieID", bookingDto.getMovieID().toString()));

        Theatre theatre = movie.getTheatres();

        User user = userRepository.findById(bookingDto.getUserId()).orElseThrow(() -> new ResourceNotFoundExcption(
                "User",
                "userID", bookingDto.getUserId().toString()));

        Shows shows =
                movie.getShows().stream().filter(e -> e.getShowId() == bookingDto.getShowId()).findFirst()
                        .orElseThrow(() -> new ResourceNotFoundExcption(
                                "Show",
                                "ShowID", bookingDto.getShowId().toString()));

        //create Booking
        Booking booking = new Booking();
        booking.setBookingDate(bookingDto.getBookingDate());
        booking.setUser(user);
        booking.setStatus(BookingStatus.PENDING);


        //create SeatReservation
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

        //create Ticket
        Ticket ticket = new Ticket();
        ticket.setStatus(BookingStatus.PENDING);
        List<Seat> seatBySeatIdn = seatRepository.findSeatBySeatIdIn(bookingDto.getSeatsId());
        Set<Seat> seats = new HashSet<>(seatBySeatIdn);
        ticket.setShows(seatReservation.getShows());
        ticket.setSeats(seats);
        ticket.setShowDate(seatReservation.getDate());
        ticket.setMovie(movie);
        Set<Ticket> tickets = new HashSet<>();
        tickets.add(ticket);
        ticket.getShows().setTickets(tickets);

        booking.setTicket(ticket);
        Set<Booking> userBookings = new HashSet<>();
        userBookings.add(booking);
        booking.getUser().setBookings(userBookings);
        booking = bookingRepository.save(booking);
        if (booking.getBookingId() == null)
            throw new BookingException("Error while creating booking");

        seatReservation = seatReservationRepository.save(seatReservation);

        if (seatReservation.getReservationId() == null)
            throw new BookingException("Error while reserving seats");
        return seatReservation;

    }

    @Override
    public Long updateBooking(Long bookingId, Long seatReservationId, BookingStatus status) {
        Booking booking = null;
        try {
            booking = bookingRepository.findById(bookingId).
                    orElseThrow(() -> new ResourceNotFoundExcption("Booking", "bookingId", bookingId.toString()));
            SeatReservation seatReservation = seatReservationRepository.findById(seatReservationId).
                    orElseThrow(() -> new ResourceNotFoundExcption("SeatReservation", "seatReservationId", seatReservationId.toString()));

            List<Long> seatIds =
                    Arrays.stream(seatReservation.getSeatIds().split(",")).map(Long::valueOf).toList();


            if (status != BookingStatus.SUCCESS || !validateBooking(seatIds, seatReservation)) {
                booking.setStatus(BookingStatus.FAILED);
                bookingRepository.save(booking);
            }

            booking.setStatus(BookingStatus.SUCCESS);
            booking.getTicket().setStatus(BookingStatus.SUCCESS);

            bookingRepository.save(booking);

        } catch (Exception ex) {
            if (booking != null) {
                booking.setStatus(BookingStatus.FAILED);
                bookingRepository.save(booking);
            }
            throw ex;

        } finally {
            seatReservationService.deleteSeatReservation(seatReservationId);
        }


        return booking.getTicket().getTicketId();
    }

    private boolean validateBooking(List<Long> seatIds, SeatReservation seatReservation) {
        List<Ticket> bookedTickets = ticketRepository.
                findByShowsAndShowDateAndSeatsSeatIdIn(seatReservation.getShows(),
                        seatReservation.getDate(), seatIds);
        return bookedTickets.stream().anyMatch(e -> BookingStatus.SUCCESS.equals(e.getStatus()));
    }

    @Override
    public List<BookingRequestDto> getAllBookings() {
        return null;
    }

    @Override
    public MovieBookingDto getBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExcption("Booking",
                "bookingID", id.toString()));
        return BookingMapper.mapToBookingDto(booking, new MovieBookingDto());
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
