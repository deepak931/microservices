package com.bookmymovie.api.bookingapp.repository;


import com.bookmymovie.api.bookingapp.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(
            "select m from Movie m inner join fetch m.theatres as t  inner join fetch t.city as c where" + " m" + ".isRunning=true and " + "m" + ".title " + "like " + "%?1% " + "and c" + ".name=?2 ")
    List<Movie> findByMovieNameAndCity(String movieName, String city);

    List<Movie> findMoviesByTitleContainingAndCityName(String movieName, String city);
}
