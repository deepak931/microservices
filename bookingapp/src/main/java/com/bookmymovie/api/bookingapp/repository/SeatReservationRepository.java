package com.bookmymovie.api.bookingapp.repository;


import com.bookmymovie.api.bookingapp.entity.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SeatReservationRepository extends JpaRepository<SeatReservation, Long> {

  List<SeatReservation> findSeatReservationByMovieIn(Set<Long> movieIDs);
}
