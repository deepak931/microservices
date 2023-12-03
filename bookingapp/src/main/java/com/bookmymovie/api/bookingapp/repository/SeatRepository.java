package com.bookmymovie.api.bookingapp.repository;


import com.bookmymovie.api.bookingapp.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findSeatBySeatIdIn(List<Long> ids);

}
