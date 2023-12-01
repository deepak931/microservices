package com.bookmymovie.api.bookingapp.repository;


import com.bookmymovie.api.bookingapp.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

}
