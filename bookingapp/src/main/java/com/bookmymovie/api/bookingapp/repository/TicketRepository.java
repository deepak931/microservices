package com.bookmymovie.api.bookingapp.repository;


import com.bookmymovie.api.bookingapp.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

  @Query(
      "select t from Ticket t inner join fetch t.shows as s inner join fetch s.movie as m " + "where m.movieId " + "in (?1) ")
  List<Ticket> findByShowsAndMovie(Set<Long> movieIds);

}
