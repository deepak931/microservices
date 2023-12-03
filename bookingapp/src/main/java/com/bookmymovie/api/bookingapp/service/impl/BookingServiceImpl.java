package com.bookmymovie.api.bookingapp.service.impl;

import com.bookmymovie.api.bookingapp.constants.BookingStatus;
import com.bookmymovie.api.bookingapp.dto.BookingRequestDto;
import com.bookmymovie.api.bookingapp.dto.MovieBookingDto;
import com.bookmymovie.api.bookingapp.entity.*;
import com.bookmymovie.api.bookingapp.exception.ResourceNotFoundExcption;
import com.bookmymovie.api.bookingapp.mapper.BookingMapper;
import com.bookmymovie.api.bookingapp.repository.*;
import com.bookmymovie.api.bookingapp.service.BookingService;
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
    public Long updateBooking(Long bookingId, Long seatReservationId, BookingStatus status) {
        Booking booking = bookingRepository.findById(bookingId).
                orElseThrow(() -> new ResourceNotFoundExcption("Booking", "bookingId", bookingId.toString()));
        SeatReservation seatReservation = seatReservationRepository.findById(seatReservationId).
                orElseThrow(() -> new ResourceNotFoundExcption("SeatReservation", "seatReservationId", seatReservationId.toString()));

        List<Long> seatIds =
                Arrays.stream(seatReservation.getSeatIds().split(",")).map(Long::valueOf).toList();

        if (status == BookingStatus.SUCCESS) {
            Ticket ticket = new Ticket();
            List<Seat> seatBySeatIdn = seatRepository.findSeatBySeatIdIn(seatIds);
            Set<Seat> seats = new HashSet<>(seatBySeatIdn);

            //            List<Set<Seat>> seatInQuery = new ArrayList<>();
            //            seatInQuery.add(seats);
            //            List<Ticket> bookedTickets = ticketRepository.findTicketBySeatsInAndShowDateAndShows(seatInQuery, seatReservation.getDate(),
            //                    seatReservation.getShows());
            //            if (!bookedTickets.isEmpty()) {
            //                String seatIdsString = seatIds.stream().map(Object::toString).collect(Collectors.joining(","));
            //                throw new SeatNotAvailableException("Selected seats are not available:" + seatIdsString);
            //            }
            ticket.setShows(seatReservation.getShows());
            ticket.setSeats(seats);
            ticket.setShowDate(seatReservation.getDate());

            booking.setStatus(BookingStatus.SUCCESS);
            Set<Booking> userBookings = new HashSet<>();
            userBookings.add(booking);
            booking.getUser().setBookings(userBookings);
            booking.setTicket(ticket);

            Set<Ticket> tickets = new HashSet<>();
            tickets.add(ticket);
            ticket.getShows().setTickets(tickets);
            bookingRepository.save(booking);

            //ticketRepository.save(ticket);
            seatReservationRepository.delete(seatReservation);
            return ticket.getTicketId();

        } else {
            booking.setStatus(BookingStatus.FAILED);

            bookingRepository.save(booking);
            seatReservationRepository.delete(seatReservation);
        }
        return null;
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
