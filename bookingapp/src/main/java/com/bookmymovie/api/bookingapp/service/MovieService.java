package com.bookmymovie.api.bookingapp.service;

import com.bookmymovie.api.bookingapp.dto.MovieDto;

import java.util.List;

public interface MovieService {

  /**
   *
   */
  void creatMovie(MovieDto movieDto);

  List<MovieDto> getAllMovies();

  MovieDto getMovieById(Long id);

  List<MovieDto> getMoviesByNameAndCity(String name, String city);

}
