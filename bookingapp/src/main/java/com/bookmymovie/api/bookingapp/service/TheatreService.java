package com.bookmymovie.api.bookingapp.service;

import com.bookmymovie.api.bookingapp.dto.TheatreDto;

import java.util.List;

public interface TheatreService {

  /**
   * @param theatreDto
   */
  void creatTheatre(TheatreDto theatreDto);

  List<TheatreDto> getAllTheatre();

  TheatreDto getTheatreById(Long id);

}
