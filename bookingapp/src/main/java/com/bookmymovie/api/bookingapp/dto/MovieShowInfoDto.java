package com.bookmymovie.api.bookingapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieShowInfoDto {
  private Long movieId;
  private String movieTitle;

  private String director;

  private String genre;

  private List<MovieTheatreDto> theatres;


}





