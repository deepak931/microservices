package com.bookmymovie.api.bookingapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieTheatreDto {
  private Long theatreId;

  private String theatreName;

  private String address;

  private String city;

  private List<MovieShowDto> showsDtoList;
}
