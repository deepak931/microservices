package com.bookmymovie.api.bookingapp.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class MovieDto {


  private Long movieId;

  private String title;

  private int year;

  private LocalDateTime releaseDate;

  private String director;

  private String genre;

  private Set<ShowsDto> shows;

  private Long theatreId;

  private Set<TheatreDto> theatreDtos;


}
