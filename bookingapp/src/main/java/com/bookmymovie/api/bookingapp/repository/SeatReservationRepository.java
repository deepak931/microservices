package com.bookmymovie.api.bookingapp.repository;


import com.bookmymovie.api.bookingapp.entity.Movie;
import com.bookmymovie.api.bookingapp.entity.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SeatReservationRepository extends JpaRepository<SeatReservation, Long> {

    List<SeatReservation> findSeatReservationByDateAndMovieIn(LocalDate date, List<Movie> movieIDs);
}
