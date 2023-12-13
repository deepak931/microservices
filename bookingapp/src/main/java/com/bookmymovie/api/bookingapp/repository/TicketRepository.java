package com.bookmymovie.api.bookingapp.repository;


import com.bookmymovie.api.bookingapp.entity.Seat;
import com.bookmymovie.api.bookingapp.entity.Shows;
import com.bookmymovie.api.bookingapp.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findTicketBySeatsInAndShowDateAndShows(List<Set<Seat>> seats, LocalDate showDate, Shows shows);

    // List<Ticket> findTicketByShowsAndShowDateAndSeats(Shows shows, LocalDate showDate, Set<Seat> seats);

    List<Ticket> findByShowsAndShowDateAndSeatsSeatIdIn(Shows shows, LocalDate showDate, Collection<Long> seats_seatId);


}
