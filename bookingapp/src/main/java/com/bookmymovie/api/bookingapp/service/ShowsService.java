package com.bookmymovie.api.bookingapp.service;

import com.bookmymovie.api.bookingapp.dto.ShowsDto;

import java.util.List;

public interface ShowsService {

  /**
   * @param showsDto
   */
  void creatShows(ShowsDto showsDto);

  List<ShowsDto> getAllShows();

}
