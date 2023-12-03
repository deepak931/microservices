package com.bookmymovie.api.bookingapp.service;

import com.bookmymovie.api.bookingapp.dto.MovieBookingDto;
import com.bookmymovie.api.bookingapp.dto.MovieDto;
import com.bookmymovie.api.bookingapp.dto.MovieRequestDto;
import com.bookmymovie.api.bookingapp.dto.MovieShowInfoDto;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {

    /**
     *
     */
    Long creatMovie(MovieRequestDto movieDto);

    List<MovieDto> getAllMovies();

    MovieDto getMovieById(Long id);

    List<MovieShowInfoDto> getMoviesByNameAndCity(String name, String city, LocalDate date);

    void test(MovieBookingDto movieDto);
}
